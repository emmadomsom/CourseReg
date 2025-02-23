
package CourseReg;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;


public class ApproveNewStudents extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Emmadom1234@";

    
    public ApproveNewStudents() {
        initComponents();
        setupTable();
        loadPendingStudents();
        addApproveButton();
    }
    
    private void setupTable(){
        String[] columns = {
            "ID", "Name", "Reg No", "Faculty", "Department", 
            "Email", "Contact", "Date of Birth", "Status"
        };
        
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        jTable1.setModel(tableModel);
        jTable1.getTableHeader().setReorderingAllowed(false);
        
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150); // Name
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100); // Reg No
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }
    
    private void loadPendingStudents(){
        tableModel.setRowCount(0);
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                "SELECT id, name, reg_no, faculty, department, email, " +
                "contact_number, dob, status FROM pending_student_registrations " +
                "WHERE status = 'PENDING' ORDER BY id DESC")) {
                 
                 while (rs.next()) {
                    Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("reg_no"),
                        rs.getString("faculty"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("contact_number"),
                        rs.getDate("dob"),
                        rs.getString("status")
                    };
                    tableModel.addRow(row);
             }
        }catch(SQLException e){
            e.printStackTrace();
             JOptionPane.showMessageDialog(this,
                "Error loading pending students: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addApproveButton(){
        JButton approveButton = new JButton("Approve Selected");
        JButton rejectButton = new JButton("Reject Selected");
        JButton refreshButton = new JButton("Refresh List");
        
        // Style buttons
        approveButton.setBackground(new Color(0, 102, 102));
        approveButton.setForeground(Color.WHITE);
        rejectButton.setBackground(new Color(153, 0, 0));
        rejectButton.setForeground(Color.WHITE);
        refreshButton.setBackground(new Color(51, 51, 51));
        refreshButton.setForeground(Color.WHITE);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(refreshButton);
        
        jPanel2.add(buttonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 460, 1350, 40));
        
        approveButton.addActionListener((ActionEvent e) -> {
            approveSelected();
        });
        
        rejectButton.addActionListener((ActionEvent e) -> {
            rejectSelected();
        });
        
        refreshButton.addActionListener((ActionEvent e) -> {
            loadPendingStudents();
        });
      
    }
    
    private void approveSelected() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a student to approve",
                "Selection Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) jTable1.getValueAt(selectedRow, 0);
        String regNo = (String) jTable1.getValueAt(selectedRow, 2);
        
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // First check if the registration number already exists in the student_details table
            try (PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT COUNT(*) FROM student_details WHERE reg_no = ?")) {
                
                checkStmt.setString(1, regNo);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(this,
                            "A student with this registration number already exists in the system",
                            "Duplicate Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            
            conn.setAutoCommit(false);
            
            String moveSql = "INSERT INTO student_details (name, reg_no, nationality, gender, " +
                        "contact_number, email, faculty, department, dob, home_address, " +
                        "city, state, country, guardian_name, guardian_contact, relationship, " +
                        "mode_of_entry) " +
                        "SELECT name, reg_no, nationality, gender, " +
                        "contact_number, email, faculty, department, dob, home_address, " +
                        "city, state, country, guardian_name, guardian_contact, relationship, " +
                        "mode_of_entry " +
                        "FROM pending_student_registrations WHERE id = ? AND reg_no = ?";

            
            try (PreparedStatement moveStmt = conn.prepareStatement(moveSql)) {
                moveStmt.setInt(1, id);
                moveStmt.setString(2, regNo);
                int rowsInserted = moveStmt.executeUpdate();
                
                if (rowsInserted == 0) {
                    throw new SQLException("Failed to insert student details, please verify the data");
                }
            
            String updateSql = "UPDATE pending_student_registrations SET status = 'APPROVED' WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, id);
                    updateStmt.executeUpdate();
            
            conn.commit();
            
            JOptionPane.showMessageDialog(this,
                "Student approved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            loadPendingStudents();
            }
          }
            
        }catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error approving student: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void rejectSelected() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a student to reject",
                "Selection Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) jTable1.getValueAt(selectedRow, 0);
        
         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(
                 "UPDATE pending_student_registrations SET status = 'REJECTED' WHERE id = ?")) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(this,
                "Student registration rejected",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            loadPendingStudents(); 
            
            } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error rejecting student: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VIEW / APPROVE NEW STUDENTS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 570, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 150));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 6, 1350, 450));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 1350, 500));

        btn_back.setBackground(new java.awt.Color(0, 102, 102));
        btn_back.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 710, 470, 30));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        ApprovePage approve = new ApprovePage();
        approve.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ApproveNewStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApproveNewStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApproveNewStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApproveNewStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApproveNewStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

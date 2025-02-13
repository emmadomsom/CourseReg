
package CourseReg;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;


public class ViewApprovePage extends javax.swing.JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USER = "root";
    private static final String PASS = "Emmadom1234@";
    private Connection conn;

    public ViewApprovePage() {
        initComponents();
        connectToDatabase();
        setupTable();
        loadRegistrations();
    }
    
    private boolean connectToDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage());
            return false;
        }
    }
    
    private void setupTable() {
        // Create table model with corrected columns
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Name", "Registration Number", "Level", "Session", "Semester", "Status", "View Courses"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Only allow editing of view courses button column
            }
        };
        
        jTable1.setModel(model);
        
        // Add button column for View Courses
        jTable1.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("View"));
        jTable1.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()) {
            @Override
            protected void onClick(int row) {
                viewCourses(row);
            }
        });
    }
    
    private void loadRegistrations() {
        try {
            String query = "SELECT r1.reg_no, r1.student_name, r1.session, r1.semester, r1.level, " +
                          "CASE WHEN r1.status IS NULL THEN 'Not Approved' " +
                          "     WHEN r1.status = 'approved' THEN 'Approved' " +
                          "     ELSE 'Not Approved' END as status " +
                          "FROM registrations r1 " +
                          "INNER JOIN ( " +
                          "    SELECT reg_no, session, semester, level, MAX(id) as latest_id " +
                          "    FROM registrations " +
                          "    GROUP BY reg_no, session, semester, level" +
                          ") r2 ON r1.reg_no = r2.reg_no " +
                          "    AND r1.session = r2.session " +
                          "    AND r1.semester = r2.semester " +
                          "    AND r1.level = r2.level " +
                          "    AND r1.id = r2.latest_id " +
                          "ORDER BY r1.session DESC, r1.semester DESC, r1.level DESC";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing rows
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("student_name"),
                    rs.getString("reg_no"),
                    rs.getString("level"),
                    rs.getString("session"),
                    rs.getString("semester"),
                    rs.getString("status"),
                    "View" // Button text
                });
            }
            if (model.getRowCount() == 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "No registrations found.",
                    "Information",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading registrations: " + e.getMessage());
        }
    }

    private void viewCourses(int row) {
        String regNo = jTable1.getValueAt(row, 1).toString();
        String session = jTable1.getValueAt(row, 3).toString();
        String semester = jTable1.getValueAt(row, 4).toString();
        String status = jTable1.getValueAt(row, 5).toString();
        
        try {
            String query = "SELECT course_code, course_name, unit_load FROM registrations " +
                          "WHERE reg_no = ? AND session = ? AND semester = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, regNo);
            pstmt.setString(2, session);
            pstmt.setString(3, semester);
            
            ResultSet rs = pstmt.executeQuery();
            
            StringBuilder courses = new StringBuilder();
            courses.append("Registered Courses:\n\n");
            
            while (rs.next()) {
                courses.append(String.format("Course Code: %s\n", rs.getString("course_code")));
                courses.append(String.format("Course Name: %s\n", rs.getString("course_name")));
                courses.append(String.format("Unit Load: %d\n", rs.getInt("unit_load")));
                courses.append("------------------------\n");
            }
            
            JTextArea textArea = new JTextArea(courses.toString());
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
            
            // Create a panel to hold the textArea and approve button
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(scrollPane);
            
            // Only show approve button if status is "Not Approved"
            if ("Not Approved".equals(status)) {
                JButton approveButton = new JButton("Approve Courses");
                approveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(Box.createRigidArea(new java.awt.Dimension(0, 10)));
                
                approveButton.addActionListener(e -> {
                    approveCourses(row);
                    JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(panel);
                    dialog.dispose();
                });
                panel.add(approveButton);
            }
            
            JDialog dialog = new JDialog(this, "Courses for " + jTable1.getValueAt(row, 0).toString(), true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.getContentPane().add(panel);
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            
            JOptionPane.showMessageDialog(this, panel, 
                "Courses for " + jTable1.getValueAt(row, 0).toString(), 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error viewing courses: " + e.getMessage());
        }
    }
    
    private void approveCourses(int row) {
        String regNo = jTable1.getValueAt(row, 1).toString();
        String session = jTable1.getValueAt(row, 3).toString();
        String semester = jTable1.getValueAt(row, 4).toString();
        String level = jTable1.getValueAt(row, 2).toString(); // Fix: Get level from table
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to approve these courses?",
            "Confirm Approval",
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                conn.setAutoCommit(false);
                
                String updateQuery = "UPDATE registrations SET status = 'approved' " +
                               "WHERE reg_no = ? AND session = ? AND semester = ? AND level = ?";
                PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                pstmt.setString(1, regNo);
                pstmt.setString(2, session);
                pstmt.setString(3, semester);
                pstmt.setString(4, level);
                
                int updated = pstmt.executeUpdate();
                
                if (updated > 0) {
                    conn.commit();
                    JOptionPane.showMessageDialog(this, "Courses approved successfully!");
                    loadRegistrations(); // Refresh the table
                }else {
                    conn.rollback();
                    JOptionPane.showMessageDialog(this, "No records were updated. Please try again.");
                
                }
                conn.setAutoCommit(true);
                
            } catch (SQLException e) {
                try {
                    conn.rollback();
                    conn.setAutoCommit(true);
                    } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error approving courses: " + e.getMessage());
            }
        }
    }
    
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setText(text);
            setOpaque(true);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }
    
    abstract class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private int currentRow;
        
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    onClick(currentRow);
                }
            });
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            currentRow = row;
            button.setText(value.toString());
            return button;
        }
        
        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
        
        protected abstract void onClick(int row);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_registrationmode = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VIEW / APPROVE COURSES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 460, -1));

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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 1420, 490));

        btn_registrationmode.setBackground(new java.awt.Color(0, 102, 102));
        btn_registrationmode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_registrationmode.setForeground(new java.awt.Color(255, 255, 255));
        btn_registrationmode.setText("SET REGISTRATION MODE");
        btn_registrationmode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrationmodeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_registrationmode, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 730, 380, 40));

        btn_back.setBackground(new java.awt.Color(0, 102, 102));
        btn_back.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 730, 310, 40));

        btn_clear.setBackground(new java.awt.Color(0, 102, 102));
        btn_clear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_clear.setForeground(new java.awt.Color(255, 255, 255));
        btn_clear.setText("CLEAR");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 780, 310, 40));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registrationmodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrationmodeActionPerformed
        SetRegMode regpage = new SetRegMode();
        regpage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_registrationmodeActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        AdminPage admin = new AdminPage();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        JOptionPane.showMessageDialog(this, 
        "Table cleared. Click 'Refresh' or restart the page to view the registrations again.",
        "Table Cleared",
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_clearActionPerformed

    
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewApprovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewApprovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewApprovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewApprovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewApprovePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_registrationmode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

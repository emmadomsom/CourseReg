
package CourseReg;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.Timer;
import java.util.HashSet;
import java.util.Set;



public class Registration extends javax.swing.JFrame {
    private static Registration currentInstance;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USER = "root";
    private static final String PASS = "Emmadom1234@";
    private Timer refreshTimer;
    private static final int REFRESH_INTERVAL = 30000; // 30 seconds
    private Set<String> notifiedRegistrations;
    private boolean isFirstLoad = true;
    
    private static String currentUserRegNo; 
    private String currentRegNo;
    
    public static void setCurrentUserRegNo(String regNo) {
        currentUserRegNo = regNo;
    }
    
    public static String getCurrentUserRegNo() {
        return currentUserRegNo;
    }
    
    
    public Registration() {
        this.currentRegNo = UserSession.getRegNo();
        currentInstance = this;
        this.notifiedRegistrations = new HashSet<>();
        System.out.println("Current Regno in Registration: " + this.currentRegNo);
        initComponents();
        setupTable();
        loadRegistrationHistory();
        setupAutoRefresh();
        setupWindowListener();
    }
    
    public static void refreshCurrentInstance() {
        if(currentInstance != null) {
            currentInstance.loadRegistrationHistory();
        }
    }
   
   private void setupAutoRefresh() {
        // Create a timer that refreshes the table every 30 seconds
        refreshTimer = new Timer(REFRESH_INTERVAL, e -> {
            loadRegistrationHistory();
        });
        refreshTimer.start();
    }
   
   @Override
    public void dispose() {
        if (refreshTimer != null && refreshTimer.isRunning()) {
            refreshTimer.stop();
        }
        super.dispose();
    }
   
   private void setupWindowListener() {
        // Add window focus listener to refresh when window regains focus
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                loadRegistrationHistory();
            }
            
            @Override
            public void windowLostFocus(WindowEvent e) {
                // Do nothing
            }
        });
    }
    
    private void loadRegistrationHistory() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Query to get distinct registration periods with status
            String query =  "SELECT session, semester, level, " +
                  "MAX(CASE WHEN status IS NULL THEN 'Not Approved' " +
                  "WHEN status = 'approved' THEN 'Approved' " +
                  "ELSE 'Not Approved' END) as approval_status " +
                  "FROM registrations " +
                  "WHERE reg_no = ? " +
                  "GROUP BY session, semester, level " +
                  "ORDER BY session DESC, semester DESC, level DESC";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, currentRegNo);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (!rs.isBeforeFirst()&& isFirstLoad ) { // Check if ResultSet is empty
                javax.swing.JOptionPane.showMessageDialog(this,
                    "No registration history found.",
                    "Information",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                isFirstLoad = false;
            }
            boolean hasUpdates = false;
            while (rs.next()) {
                String session = rs.getString("session");
                String semester = rs.getString("semester");
                String level = rs.getString("level");
                String status = rs.getString("approval_status");
                
                model.addRow(new Object[]{session, semester, level, status});
                
                String registrationKey = session + "-" + semester + "-" + level;
                
                if ("Approved".equals(status) && !notifiedRegistrations.contains(registrationKey)) {
                    hasUpdates = true;
                    notifiedRegistrations.add(registrationKey);

                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error loading registration history: " + ex.getMessage(),
                "Database Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setupTable() {
        // Make table non-editable and set column properties
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "SESSION", "SEMESTER", "LEVEL", "STATUS"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12));
        jTable1.getTableHeader().setFont(new java.awt.Font("Tahoma", 1, 12));
        
        // Set column widths
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
        
        // Center align all columns
        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    
     private boolean hasUnApprovedRegistration() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String query = "SELECT COUNT(*) as count FROM registrations " +
                          "WHERE reg_no = ? AND (status IS NULL OR status != 'approved')";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, currentRegNo);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_register = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_load = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COURSE REGISTRATION HISTORY");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 380, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 150));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SESSION", "SEMESTER", "LEVEL", "STATUS"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 1150, -1));

        btn_register.setBackground(new java.awt.Color(0, 102, 102));
        btn_register.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_register.setForeground(new java.awt.Color(255, 255, 255));
        btn_register.setText("REGISTER COURSES");
        btn_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registerActionPerformed(evt);
            }
        });
        getContentPane().add(btn_register, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 640, 390, 40));

        btn_back.setBackground(new java.awt.Color(0, 102, 102));
        btn_back.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 650, 400, 40));

        btn_load.setBackground(new java.awt.Color(0, 102, 102));
        btn_load.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_load.setForeground(new java.awt.Color(255, 255, 255));
        btn_load.setText("LOAD HISTORY");
        btn_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadActionPerformed(evt);
            }
        });
        getContentPane().add(btn_load, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 700, 280, 40));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        HomePage home = new HomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed
        if (hasUnApprovedRegistration()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "You have pending registration awaiting approval.",
                "Registration Status",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            
        }
        
        ConfimPage confirm = new ConfimPage();
        confirm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_registerActionPerformed

    private void btn_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadActionPerformed
        loadRegistrationHistory();
    }//GEN-LAST:event_btn_loadActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_load;
    private javax.swing.JButton btn_register;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

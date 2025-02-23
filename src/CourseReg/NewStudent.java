
package CourseReg;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewStudent extends javax.swing.JFrame {
    private String studentName;
    private static final Logger LOGGER = Logger.getLogger(NewStudent.class.getName());
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Emmadom1234@"; 
   

    
    public NewStudent() {
        initComponents();
        panelPassword.setVisible(false);
        
         txt_password.setText("");
         txt_confirm.setText("");
        
        checkbox_password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkbox_password.isSelected()) {
                    txt_password.setEchoChar((char) 0);
                } else {
                    txt_password.setEchoChar('•');
                }
            }
        });

        checkbox_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkbox_confirm.isSelected()) {
                    txt_confirm.setEchoChar((char) 0);
                } else {
                    txt_confirm.setEchoChar('•');
                }
            }
        });
    }
    
    public void setStudentName(String name) {
        this.studentName = name;
    }
    
    
    private void checkAdminApproval(){
        String regNo = txt_regno.getText().trim();
        
        if (regNo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter your registration number",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
            // First check if user account already exists
            String checkUserSql = "SELECT * FROM user WHERE username = ?";
            try (PreparedStatement checkUserStmt = conn.prepareStatement(checkUserSql)) {
                checkUserStmt.setString(1, regNo);
                try (ResultSet userRs = checkUserStmt.executeQuery()) {
                    if (userRs.next()) {
                        JOptionPane.showMessageDialog(this,
                            "Account already exists for this registration number",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            // Check if student is in pending_student_registrations and get their status
            String sql = "SELECT name, status FROM pending_student_registrations WHERE reg_no = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, regNo);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String status = rs.getString("status");
                        // Only proceed if status is "APPROVED"
                        if ("APPROVED".equalsIgnoreCase(status)) {
                            String name = rs.getString("name");
                            setStudentName(name);
                        
                        JOptionPane.showMessageDialog(this,
                            "Your registration has been approved! You can now create your account.",
                            "Approved",
                            JOptionPane.INFORMATION_MESSAGE);
                            
                        panelPassword.setVisible(true);
                        btn_check.setVisible(false);
                        txt_regno.setEditable(false);
                    } else {
                            JOptionPane.showMessageDialog(this,
                                "Your registration is still pending admin approval.",
                                "Pending Approval",
                                JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "Registration number not found in pending registrations.",
                            "Not Found",
                            JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Database error while checking approval status", e);
            JOptionPane.showMessageDialog(this,
                "Error checking approval status: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error hashing password: " + e.getMessage(),
                "Security Error",
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    private void createStudentAccount(){
        String regNo = txt_regno.getText().trim();
        String password = new String(txt_password.getPassword()).trim();
        String confirmPassword = new String(txt_confirm.getPassword()).trim();
        
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter and confirm your password",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                "Passwords do not match",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String hashedPassword = hashPassword(password);
        if (hashedPassword == null) {
            return; // Error message already shown in hashPassword method
        }
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Double-check approval status before creating account
            String checkApprovalSql = "SELECT status FROM pending_student_registrations WHERE reg_no = ? AND status = 'APPROVED'";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkApprovalSql)) {
                checkStmt.setString(1, regNo);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(this,
                            "Error: Your registration is not approved. Cannot create account.",
                            "Not Approved",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        
        String sql = "INSERT INTO user (username, password, name, level) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, regNo);
                pstmt.setString(2, hashedPassword);
                pstmt.setString(3, studentName);
                pstmt.setString(4, "100 LEVEL");
                
                int result = pstmt.executeUpdate();
                
                if (result > 0) {
                    JOptionPane.showMessageDialog(this,
                        "Account created successfully! You can now login.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    new LoginPage().setVisible(true);
                    this.dispose();
                }
            }
    }catch(SQLException e){
        e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error creating account: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelPassword = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_create = new javax.swing.JButton();
        checkbox_password = new javax.swing.JCheckBox();
        checkbox_confirm = new javax.swing.JCheckBox();
        txt_confirm = new javax.swing.JPasswordField();
        txt_password = new javax.swing.JPasswordField();
        btn_check = new javax.swing.JButton();
        txt_regno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("REG NUMBER: ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 100, 20));

        panelPassword.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("CONFIRM PASSWORD :");
        panelPassword.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 140, 20));

        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("PASSWORD :");
        panelPassword.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 100, 20));

        btn_create.setBackground(new java.awt.Color(0, 102, 102));
        btn_create.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_create.setForeground(new java.awt.Color(255, 255, 255));
        btn_create.setText("CREATE STUDENT ACCOUNT");
        btn_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createActionPerformed(evt);
            }
        });
        panelPassword.add(btn_create, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 230, -1));

        checkbox_password.setText("Show Password");
        panelPassword.add(checkbox_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        checkbox_confirm.setText("Show Password");
        panelPassword.add(checkbox_confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, -1, -1));

        txt_confirm.setText("jPasswordField1");
        panelPassword.add(txt_confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 350, 30));

        txt_password.setText("jPasswordField1");
        panelPassword.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 350, 30));

        jPanel2.add(panelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 580, 240));

        btn_check.setBackground(new java.awt.Color(0, 102, 102));
        btn_check.setForeground(new java.awt.Color(255, 255, 255));
        btn_check.setText("CHECK FOR APPROVAL");
        btn_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkActionPerformed(evt);
            }
        });
        jPanel2.add(btn_check, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 190, -1));
        jPanel2.add(txt_regno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 360, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 740, 470));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NEW STUDENT");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 350, 130));

        btn_back.setBackground(new java.awt.Color(0, 102, 102));
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel1.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 770, 420, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 900));

        setSize(new java.awt.Dimension(1601, 909));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createActionPerformed
        createStudentAccount();
    }//GEN-LAST:event_btn_createActionPerformed

    private void btn_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_checkActionPerformed
        checkAdminApproval();
    }//GEN-LAST:event_btn_checkActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        LoginPage login = new LoginPage();
        login.setVisible(true);
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
            java.util.logging.Logger.getLogger(NewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_check;
    private javax.swing.JButton btn_create;
    private javax.swing.JCheckBox checkbox_confirm;
    private javax.swing.JCheckBox checkbox_password;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelPassword;
    private javax.swing.JPasswordField txt_confirm;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_regno;
    // End of variables declaration//GEN-END:variables
}

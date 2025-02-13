/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CourseReg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class AdminLoginPage extends javax.swing.JFrame {

    /**
     * Creates new form AdminLoginPage
     */
    public AdminLoginPage() {
        initComponents();
    }
    void userVerification(String username, String password){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db","root","Emmadom1234@");
            String sql = "SELECT username, password FROM user WHERE username = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, username);
            pst.setString(2, password);
            try(ResultSet rs = pst.executeQuery()){
            if(rs.next()){
                lbl_error.setText("");
                JOptionPane.showMessageDialog(this,"Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE );
                
                AdminPage admin = new AdminPage();
                admin.show();
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
           }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_login = new javax.swing.JButton();
        txt_password = new javax.swing.JPasswordField();
        lbl_error = new javax.swing.JLabel();
        chk_showPassword = new javax.swing.JCheckBox();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("PASSWORD :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("ADMIN USERNAME :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        txt_username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 410, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("ADMIN LOGIN");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 170, -1));

        btn_login.setBackground(new java.awt.Color(0, 51, 51));
        btn_login.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setText("LOGIN");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        jPanel2.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 160, -1));

        txt_password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 410, 30));

        lbl_error.setForeground(new java.awt.Color(255, 51, 0));
        jPanel2.add(lbl_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 280, 20));

        chk_showPassword.setText("Show Password");
        chk_showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_showPasswordActionPerformed(evt);
            }
        });
        jPanel2.add(chk_showPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, 20));

        btn_back.setBackground(new java.awt.Color(0, 51, 51));
        btn_back.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel2.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 100, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 560, 380));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 700));

        setSize(new java.awt.Dimension(916, 709));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        String username = txt_username.getText();
        String password = new String(txt_password.getPassword());
     
     
     if(username.trim().equals("")||password.trim().equals(""))
     {
         lbl_error.setText("Please enter username and password");
     }else{
         userVerification(username,password);
     }
    
    }//GEN-LAST:event_btn_loginActionPerformed

    private void chk_showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_showPasswordActionPerformed
        if (chk_showPassword.isSelected()) {
        txt_password.setEchoChar((char) 0); // Show password as plain text
    } else {
        txt_password.setEchoChar('*'); // Mask password
        }
    }//GEN-LAST:event_chk_showPasswordActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        IntroPage intro = new IntroPage();
        intro.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminLoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_login;
    private javax.swing.JCheckBox chk_showPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}

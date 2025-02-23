package CourseReg;

import java.awt.Color;


public class FirstPage extends javax.swing.JFrame {

    
    public FirstPage() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelLogin = new javax.swing.JPanel();
        lbl_studentlogin = new javax.swing.JLabel();
        panelCreate = new javax.swing.JPanel();
        lbl_create = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("STUDENTS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 250, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 200));

        panelLogin.setBackground(new java.awt.Color(0, 102, 102));
        panelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_studentlogin.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_studentlogin.setForeground(new java.awt.Color(255, 255, 255));
        lbl_studentlogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_studentlogin.setText("STUDENT LOGIN");
        lbl_studentlogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_studentloginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_studentloginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_studentloginMouseExited(evt);
            }
        });
        panelLogin.add(lbl_studentlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 440, 200));

        getContentPane().add(panelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 510, 260));

        panelCreate.setBackground(new java.awt.Color(0, 102, 102));
        panelCreate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_create.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_create.setForeground(new java.awt.Color(255, 255, 255));
        lbl_create.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_create.setText("CREATE STUDENT ACCOUNT");
        lbl_create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_createMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_createMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_createMouseExited(evt);
            }
        });
        panelCreate.add(lbl_create, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 470, 200));

        getContentPane().add(panelCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 320, 510, 270));

        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 760, 110, 30));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        IntroPage intro = new IntroPage();
        intro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void lbl_studentloginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_studentloginMouseEntered
        Color clr = new Color(0,51,51);
        panelLogin.setBackground(clr);
    }//GEN-LAST:event_lbl_studentloginMouseEntered

    private void lbl_studentloginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_studentloginMouseExited
        Color clr = new Color(0,102,102);
        panelLogin.setBackground(clr);
    }//GEN-LAST:event_lbl_studentloginMouseExited

    private void lbl_createMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_createMouseEntered
        Color clr = new Color(0,51,51);
        panelCreate.setBackground(clr);
    }//GEN-LAST:event_lbl_createMouseEntered

    private void lbl_createMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_createMouseExited
        Color clr = new Color(0,102,102);
        panelCreate.setBackground(clr);
    }//GEN-LAST:event_lbl_createMouseExited

    private void lbl_studentloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_studentloginMouseClicked
        LoginPage login = new LoginPage();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_studentloginMouseClicked

    private void lbl_createMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_createMouseClicked
        StudentForm create = new StudentForm();
        create.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_createMouseClicked

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FirstPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_create;
    private javax.swing.JLabel lbl_studentlogin;
    private javax.swing.JPanel panelCreate;
    private javax.swing.JPanel panelLogin;
    // End of variables declaration//GEN-END:variables
}

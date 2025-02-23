
package CourseReg;

import java.awt.Color;


public class IntroPage extends javax.swing.JFrame {

    
    public IntroPage() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel_student = new javax.swing.JPanel();
        lbl_student = new javax.swing.JLabel();
        panel_admin = new javax.swing.JPanel();
        lbl_admin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DEPARTMENT OF COMPUTER SCIENCE,");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, -1, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UNIVERSITY OF NIGERIA, NSUKKA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 200));

        panel_student.setBackground(new java.awt.Color(0, 102, 102));
        panel_student.setForeground(new java.awt.Color(0, 102, 102));
        panel_student.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_student.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbl_student.setForeground(new java.awt.Color(255, 255, 255));
        lbl_student.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_student.setText("STUDENT");
        lbl_student.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_studentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_studentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_studentMouseExited(evt);
            }
        });
        panel_student.add(lbl_student, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 390, 170));

        getContentPane().add(panel_student, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 480, 250));

        panel_admin.setBackground(new java.awt.Color(0, 102, 102));
        panel_admin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_admin.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbl_admin.setForeground(new java.awt.Color(255, 255, 255));
        lbl_admin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_admin.setText("ADMIN");
        lbl_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_adminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_adminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_adminMouseExited(evt);
            }
        });
        panel_admin.add(lbl_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 390, 170));

        getContentPane().add(panel_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 390, 480, 250));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_studentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_studentMouseEntered
        Color clr = new Color(0,51,51);
        panel_student.setBackground(clr);
    }//GEN-LAST:event_lbl_studentMouseEntered

    private void lbl_studentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_studentMouseExited
        Color clr = new Color(0,102,102);
        panel_student.setBackground(clr);
    }//GEN-LAST:event_lbl_studentMouseExited

    private void lbl_adminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_adminMouseEntered
        Color clr = new Color(0,51,51);
        panel_admin.setBackground(clr);
    }//GEN-LAST:event_lbl_adminMouseEntered

    private void lbl_adminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_adminMouseExited
        Color clr = new Color(0,102,102);
        panel_admin.setBackground(clr);
    }//GEN-LAST:event_lbl_adminMouseExited

    private void lbl_studentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_studentMouseClicked
        FirstPage first = new FirstPage();
        first.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_studentMouseClicked

    private void lbl_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_adminMouseClicked
        AdminLoginPage login = new AdminLoginPage();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_adminMouseClicked

    
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
            java.util.logging.Logger.getLogger(IntroPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IntroPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IntroPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IntroPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IntroPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_admin;
    private javax.swing.JLabel lbl_student;
    private javax.swing.JPanel panel_admin;
    private javax.swing.JPanel panel_student;
    // End of variables declaration//GEN-END:variables
}

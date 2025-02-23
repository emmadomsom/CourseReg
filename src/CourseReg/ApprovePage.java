
package CourseReg;

import java.awt.Color;


public class ApprovePage extends javax.swing.JFrame {


    public ApprovePage() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelApproveStudent = new javax.swing.JPanel();
        lbl_approvestudent = new javax.swing.JLabel();
        panelCourses = new javax.swing.JPanel();
        lbl_approvecourses = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("APPROVE PAGE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 350, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 150));

        panelApproveStudent.setBackground(new java.awt.Color(0, 102, 102));
        panelApproveStudent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_approvestudent.setBackground(new java.awt.Color(255, 255, 255));
        lbl_approvestudent.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_approvestudent.setForeground(new java.awt.Color(255, 255, 255));
        lbl_approvestudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_approvestudent.setText("APPROVE NEW STUDENT");
        lbl_approvestudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_approvestudentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_approvestudentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_approvestudentMouseExited(evt);
            }
        });
        panelApproveStudent.add(lbl_approvestudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 410, 230));

        getContentPane().add(panelApproveStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 460, 290));

        panelCourses.setBackground(new java.awt.Color(0, 102, 102));
        panelCourses.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_approvecourses.setBackground(new java.awt.Color(255, 255, 255));
        lbl_approvecourses.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        lbl_approvecourses.setForeground(new java.awt.Color(255, 255, 255));
        lbl_approvecourses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_approvecourses.setText("APPROVE STUDENT COURSES");
        lbl_approvecourses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_approvecoursesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_approvecoursesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_approvecoursesMouseExited(evt);
            }
        });
        panelCourses.add(lbl_approvecourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 410, 230));

        getContentPane().add(panelCourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 320, 460, 290));

        btn_back.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 730, 110, 30));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_approvestudentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_approvestudentMouseEntered
        Color clr = new Color(0,51,51);
        panelApproveStudent.setBackground(clr);
    }//GEN-LAST:event_lbl_approvestudentMouseEntered

    private void lbl_approvestudentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_approvestudentMouseExited
        Color clr = new Color(0,102,102);
        panelApproveStudent.setBackground(clr);
    }//GEN-LAST:event_lbl_approvestudentMouseExited

    private void lbl_approvestudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_approvestudentMouseClicked
        ApproveNewStudents approve = new ApproveNewStudents();
        approve.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_approvestudentMouseClicked

    private void lbl_approvecoursesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_approvecoursesMouseEntered
        Color clr = new Color(0,51,51);
        panelCourses.setBackground(clr);
    }//GEN-LAST:event_lbl_approvecoursesMouseEntered

    private void lbl_approvecoursesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_approvecoursesMouseExited
        Color clr = new Color(0,102,102);
        panelCourses.setBackground(clr);
    }//GEN-LAST:event_lbl_approvecoursesMouseExited

    private void lbl_approvecoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_approvecoursesMouseClicked
        ViewApprovePage approve = new ViewApprovePage();
        approve.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_approvecoursesMouseClicked

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        AdminPage admin = new AdminPage();
        admin.setVisible(true);
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
            java.util.logging.Logger.getLogger(ApprovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApprovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApprovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApprovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApprovePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_approvecourses;
    private javax.swing.JLabel lbl_approvestudent;
    private javax.swing.JPanel panelApproveStudent;
    private javax.swing.JPanel panelCourses;
    // End of variables declaration//GEN-END:variables
}

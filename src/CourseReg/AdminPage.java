
package CourseReg;

import java.awt.Color;


public class AdminPage extends javax.swing.JFrame {

    
    public AdminPage() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        approvePanel = new javax.swing.JPanel();
        lbl_approvecourses = new javax.swing.JLabel();
        insertPanel = new javax.swing.JPanel();
        lbl_insertdelete = new javax.swing.JLabel();
        viewPanel = new javax.swing.JPanel();
        lbl_view = new javax.swing.JLabel();
        panel_insertoutline = new javax.swing.JPanel();
        lbl_insertoutline = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        panelView = new javax.swing.JPanel();
        lbl_viewoutline = new javax.swing.JLabel();
        panelRegMode = new javax.swing.JPanel();
        lbl_setRegMode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADMIN PAGE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 230, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 160));

        approvePanel.setBackground(new java.awt.Color(0, 102, 102));
        approvePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_approvecourses.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        lbl_approvecourses.setForeground(new java.awt.Color(255, 255, 255));
        lbl_approvecourses.setText("APPROVE STUDENTS/COURSES");
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
        approvePanel.add(lbl_approvecourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 330, 190));

        getContentPane().add(approvePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 390, 250));

        insertPanel.setBackground(new java.awt.Color(0, 102, 102));
        insertPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_insertdelete.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_insertdelete.setForeground(new java.awt.Color(255, 255, 255));
        lbl_insertdelete.setText("INSERT / DELETE COURSES");
        lbl_insertdelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_insertdeleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_insertdeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_insertdeleteMouseExited(evt);
            }
        });
        insertPanel.add(lbl_insertdelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 310, 170));

        getContentPane().add(insertPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 380, 250));

        viewPanel.setBackground(new java.awt.Color(0, 102, 102));
        viewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_view.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_view.setForeground(new java.awt.Color(255, 255, 255));
        lbl_view.setText("VIEW COURSES AVAILABLE");
        lbl_view.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_viewMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_viewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_viewMouseExited(evt);
            }
        });
        viewPanel.add(lbl_view, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 320, 170));

        getContentPane().add(viewPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 560, 390, 250));

        panel_insertoutline.setBackground(new java.awt.Color(0, 102, 102));
        panel_insertoutline.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_insertoutline.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_insertoutline.setForeground(new java.awt.Color(255, 255, 255));
        lbl_insertoutline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_insertoutline.setText("INSERT / DELETE COURSE OUTLINE");
        lbl_insertoutline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_insertoutlineMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_insertoutlineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_insertoutlineMouseExited(evt);
            }
        });
        panel_insertoutline.add(lbl_insertoutline, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 310, 170));

        getContentPane().add(panel_insertoutline, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 570, 380, 240));

        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 770, 120, 30));

        panelView.setBackground(new java.awt.Color(0, 102, 102));
        panelView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_viewoutline.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_viewoutline.setForeground(new java.awt.Color(255, 255, 255));
        lbl_viewoutline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_viewoutline.setText("VIEW COURSE OUTLINE");
        lbl_viewoutline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_viewoutlineMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_viewoutlineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_viewoutlineMouseExited(evt);
            }
        });
        panelView.add(lbl_viewoutline, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 290, 170));

        getContentPane().add(panelView, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 250, 370, 250));

        panelRegMode.setBackground(new java.awt.Color(0, 102, 102));
        panelRegMode.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_setRegMode.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_setRegMode.setForeground(new java.awt.Color(255, 255, 255));
        lbl_setRegMode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_setRegMode.setText("SET REGISTRATION MODE");
        lbl_setRegMode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_setRegModeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_setRegModeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_setRegModeMouseExited(evt);
            }
        });
        panelRegMode.add(lbl_setRegMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 330, 170));

        getContentPane().add(panelRegMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 570, 370, 240));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_approvecoursesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_approvecoursesMouseEntered
        Color clr = new Color(0,51,51);
        approvePanel.setBackground(clr);
    }//GEN-LAST:event_lbl_approvecoursesMouseEntered

    private void lbl_approvecoursesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_approvecoursesMouseExited
        Color clr = new Color(0,102,102);
        approvePanel.setBackground(clr);
    }//GEN-LAST:event_lbl_approvecoursesMouseExited

    private void lbl_insertdeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertdeleteMouseEntered
        Color clr = new Color(0,51,51);
        insertPanel.setBackground(clr);
    }//GEN-LAST:event_lbl_insertdeleteMouseEntered

    private void lbl_insertdeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertdeleteMouseExited
        Color clr = new Color(0,102,102);
        insertPanel.setBackground(clr);
    }//GEN-LAST:event_lbl_insertdeleteMouseExited

    private void lbl_viewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_viewMouseEntered
        Color clr = new Color(0,51,51);
        viewPanel.setBackground(clr);
    }//GEN-LAST:event_lbl_viewMouseEntered

    private void lbl_viewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_viewMouseExited
        Color clr = new Color(0,102,102);
        viewPanel.setBackground(clr);
    }//GEN-LAST:event_lbl_viewMouseExited

    private void lbl_insertdeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertdeleteMouseClicked
        InsertDeletePage insertdelete = new InsertDeletePage();
        insertdelete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_insertdeleteMouseClicked

    private void lbl_insertoutlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertoutlineMouseClicked
        InsertDeleteOutline outline = new InsertDeleteOutline();
        outline.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_insertoutlineMouseClicked

    private void lbl_insertoutlineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertoutlineMouseEntered
        Color clr = new Color(0,51,51);
        panel_insertoutline.setBackground(clr);
    }//GEN-LAST:event_lbl_insertoutlineMouseEntered

    private void lbl_insertoutlineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertoutlineMouseExited
        Color clr = new Color(0,102,102);
        panel_insertoutline.setBackground(clr);
    }//GEN-LAST:event_lbl_insertoutlineMouseExited

    private void lbl_viewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_viewMouseClicked
        CoursesAvailable courses = new CoursesAvailable();
        courses.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_viewMouseClicked

    private void lbl_approvecoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_approvecoursesMouseClicked
        ApprovePage approve = new ApprovePage();
        approve.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_approvecoursesMouseClicked

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        AdminLoginPage login = new AdminLoginPage();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void lbl_viewoutlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_viewoutlineMouseClicked
        ConfirmViewOutline confirm = new ConfirmViewOutline();
        confirm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_viewoutlineMouseClicked

    private void lbl_viewoutlineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_viewoutlineMouseEntered
        Color clr = new Color(0,51,51);
        panelView.setBackground(clr);
    }//GEN-LAST:event_lbl_viewoutlineMouseEntered

    private void lbl_viewoutlineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_viewoutlineMouseExited
        Color clr = new Color(0,102,102);
        panelView.setBackground(clr);
    }//GEN-LAST:event_lbl_viewoutlineMouseExited

    private void lbl_setRegModeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_setRegModeMouseEntered
        Color clr = new Color(0,51,51);
        panelRegMode.setBackground(clr);
    }//GEN-LAST:event_lbl_setRegModeMouseEntered

    private void lbl_setRegModeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_setRegModeMouseExited
       Color clr = new Color(0,102,102);
        panelRegMode.setBackground(clr);
    }//GEN-LAST:event_lbl_setRegModeMouseExited

    private void lbl_setRegModeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_setRegModeMouseClicked
        SetRegMode regmode = new SetRegMode();
        regmode.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_setRegModeMouseClicked

    
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel approvePanel;
    private javax.swing.JButton btn_back;
    private javax.swing.JPanel insertPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_approvecourses;
    private javax.swing.JLabel lbl_insertdelete;
    private javax.swing.JLabel lbl_insertoutline;
    private javax.swing.JLabel lbl_setRegMode;
    private javax.swing.JLabel lbl_view;
    private javax.swing.JLabel lbl_viewoutline;
    private javax.swing.JPanel panelRegMode;
    private javax.swing.JPanel panelView;
    private javax.swing.JPanel panel_insertoutline;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
}

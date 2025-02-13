
package CourseReg;

import java.awt.Color;


public class HomePage extends javax.swing.JFrame {

    
    public HomePage() {
        initComponents();
        
        String name = UserSession.getName();
        txt_name.setText(name);
        txt_name.setText(name.toUpperCase());
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_name = new javax.swing.JLabel();
        panel_student = new javax.swing.JPanel();
        lbl_student = new javax.swing.JLabel();
        panel_coursereg = new javax.swing.JPanel();
        lbl_coursereg = new javax.swing.JLabel();
        panel_outline = new javax.swing.JPanel();
        lbl_outline = new javax.swing.JLabel();
        lbl_Info = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("UNIVERSITY OF NIGERIA,NSUKKA");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 480, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DEPARTMENT OF COMPUTER SCIENCE");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 400, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 160));

        txt_name.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txt_name.setForeground(new java.awt.Color(0, 153, 153));
        getContentPane().add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 490, 40));

        panel_student.setBackground(new java.awt.Color(0, 102, 102));
        panel_student.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_student.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_student.setForeground(new java.awt.Color(255, 255, 255));
        lbl_student.setText("STUDENT DETAILS");
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
        panel_student.add(lbl_student, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 230, 150));

        getContentPane().add(panel_student, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 390, 240));

        panel_coursereg.setBackground(new java.awt.Color(0, 102, 102));

        lbl_coursereg.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_coursereg.setForeground(new java.awt.Color(255, 255, 255));
        lbl_coursereg.setText("COURSE REGISTRATION");
        lbl_coursereg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_courseregMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_courseregMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_courseregMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel_courseregLayout = new javax.swing.GroupLayout(panel_coursereg);
        panel_coursereg.setLayout(panel_courseregLayout);
        panel_courseregLayout.setHorizontalGroup(
            panel_courseregLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_courseregLayout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(lbl_coursereg, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        panel_courseregLayout.setVerticalGroup(
            panel_courseregLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_courseregLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lbl_coursereg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        getContentPane().add(panel_coursereg, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, 400, 240));

        panel_outline.setBackground(new java.awt.Color(0, 102, 102));
        panel_outline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_outlineMouseEntered(evt);
            }
        });

        lbl_outline.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_outline.setForeground(new java.awt.Color(255, 255, 255));
        lbl_outline.setText("COURSE OUTLINE");
        lbl_outline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_outlineMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_outlineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_outlineMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel_outlineLayout = new javax.swing.GroupLayout(panel_outline);
        panel_outline.setLayout(panel_outlineLayout);
        panel_outlineLayout.setHorizontalGroup(
            panel_outlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_outlineLayout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(lbl_outline, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        panel_outlineLayout.setVerticalGroup(
            panel_outlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_outlineLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbl_outline, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(panel_outline, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 380, 400, 240));

        lbl_Info.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_Info.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(lbl_Info, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 180, 460, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("WELCOME");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 180, 40));

        btn_back.setBackground(new java.awt.Color(0, 102, 102));
        btn_back.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 790, 300, 30));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_studentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_studentMouseEntered
        Color clr = new Color(0,51,51);
        panel_student.setBackground(clr);
    }//GEN-LAST:event_lbl_studentMouseEntered

    private void lbl_courseregMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_courseregMouseEntered
        Color clr = new Color(0,51,51);
        panel_coursereg.setBackground(clr);
    }//GEN-LAST:event_lbl_courseregMouseEntered

    private void panel_outlineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_outlineMouseEntered
       
    }//GEN-LAST:event_panel_outlineMouseEntered

    private void lbl_courseregMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_courseregMouseExited
        Color clr = new Color(0,102,102);
        panel_coursereg.setBackground(clr);
    }//GEN-LAST:event_lbl_courseregMouseExited

    private void lbl_outlineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_outlineMouseEntered
        Color clr = new Color(0,51,51);
        panel_outline.setBackground(clr);
    }//GEN-LAST:event_lbl_outlineMouseEntered

    private void lbl_studentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_studentMouseExited
        Color clr = new Color(0,102,102);
        panel_student.setBackground(clr);
    }//GEN-LAST:event_lbl_studentMouseExited

    private void lbl_outlineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_outlineMouseExited
        Color clr = new Color(0,102,102);
        panel_outline.setBackground(clr);
    }//GEN-LAST:event_lbl_outlineMouseExited

    private void lbl_courseregMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_courseregMouseClicked
        Registration reg = new Registration();
        reg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_courseregMouseClicked

    private void lbl_outlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_outlineMouseClicked
        Outline outline = new Outline();
        outline.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_outlineMouseClicked

    private void lbl_studentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_studentMouseClicked
        Details details = new Details();
        details.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_studentMouseClicked

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        LoginPage login = new LoginPage();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    
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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_Info;
    private javax.swing.JLabel lbl_coursereg;
    private javax.swing.JLabel lbl_outline;
    private javax.swing.JLabel lbl_student;
    private javax.swing.JPanel panel_coursereg;
    private javax.swing.JPanel panel_outline;
    private javax.swing.JPanel panel_student;
    private javax.swing.JLabel txt_name;
    // End of variables declaration//GEN-END:variables
}

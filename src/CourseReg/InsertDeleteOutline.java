

package CourseReg;

import java.awt.Color;


public class InsertDeleteOutline extends javax.swing.JFrame {

    
    public InsertDeleteOutline() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelInsert = new javax.swing.JPanel();
        lbl_insertoutline = new javax.swing.JLabel();
        panelDelete = new javax.swing.JPanel();
        lbl_deleteoutline = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INSERT / DELETE COURSE OUTLINE");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 600, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 180));

        panelInsert.setBackground(new java.awt.Color(0, 102, 102));
        panelInsert.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_insertoutline.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_insertoutline.setForeground(new java.awt.Color(255, 255, 255));
        lbl_insertoutline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_insertoutline.setText("INSERT COURSE OUTLINE");
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
        panelInsert.add(lbl_insertoutline, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 420, 230));

        getContentPane().add(panelInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 510, 300));

        panelDelete.setBackground(new java.awt.Color(0, 102, 102));
        panelDelete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_deleteoutline.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_deleteoutline.setForeground(new java.awt.Color(255, 255, 255));
        lbl_deleteoutline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_deleteoutline.setText("DELETE COURSE OUTLINE");
        lbl_deleteoutline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_deleteoutlineMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_deleteoutlineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_deleteoutlineMouseExited(evt);
            }
        });
        panelDelete.add(lbl_deleteoutline, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 430, 250));

        getContentPane().add(panelDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 380, 510, 300));

        btn_back.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_back.setText("BACK");
        btn_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_backMouseClicked(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 790, 130, 40));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_insertoutlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertoutlineMouseClicked
        InsertOutline insert = new InsertOutline();
        insert.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_insertoutlineMouseClicked

    private void lbl_insertoutlineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertoutlineMouseEntered
        Color clr = new Color(0,51,51);
        panelInsert.setBackground(clr);
    }//GEN-LAST:event_lbl_insertoutlineMouseEntered

    private void lbl_insertoutlineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertoutlineMouseExited
        Color clr = new Color(0,102,102);
        panelInsert.setBackground(clr);
    }//GEN-LAST:event_lbl_insertoutlineMouseExited

    private void lbl_deleteoutlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_deleteoutlineMouseClicked
        DeleteOutline delete = new DeleteOutline();
        delete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_deleteoutlineMouseClicked

    private void lbl_deleteoutlineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_deleteoutlineMouseEntered
        Color clr = new Color(0,51,51);
        panelDelete.setBackground(clr);
    }//GEN-LAST:event_lbl_deleteoutlineMouseEntered

    private void lbl_deleteoutlineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_deleteoutlineMouseExited
        Color clr = new Color(0,102,102);
        panelDelete.setBackground(clr);
    }//GEN-LAST:event_lbl_deleteoutlineMouseExited

    private void btn_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backMouseClicked
        AdminPage admin = new AdminPage();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backMouseClicked

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InsertDeleteOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertDeleteOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertDeleteOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertDeleteOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertDeleteOutline().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_deleteoutline;
    private javax.swing.JLabel lbl_insertoutline;
    private javax.swing.JPanel panelDelete;
    private javax.swing.JPanel panelInsert;
    // End of variables declaration//GEN-END:variables
}

package CourseReg;

import java.awt.Color;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Pablo
 */
public class InsertDeletePage extends javax.swing.JFrame {

    /**
     * Creates new form InsertDeletePage
     */
    public InsertDeletePage() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        insertPanel = new javax.swing.JPanel();
        lbl_insert = new javax.swing.JLabel();
        deletePanel = new javax.swing.JPanel();
        lbl_delete = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INSERT/DELETE COURSES");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 430, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 180));

        insertPanel.setBackground(new java.awt.Color(0, 102, 102));
        insertPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_insert.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_insert.setForeground(new java.awt.Color(255, 255, 255));
        lbl_insert.setText("INSERT COURSES");
        lbl_insert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_insertMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_insertMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_insertMouseExited(evt);
            }
        });
        insertPanel.add(lbl_insert, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 330, 170));

        getContentPane().add(insertPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 470, 260));

        deletePanel.setBackground(new java.awt.Color(0, 102, 102));
        deletePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_delete.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_delete.setForeground(new java.awt.Color(255, 255, 255));
        lbl_delete.setText("DELETE COURSES");
        lbl_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_deleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_deleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_deleteMouseExited(evt);
            }
        });
        deletePanel.add(lbl_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 330, 170));

        getContentPane().add(deletePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, 480, 260));

        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 753, 120, 40));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_insertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertMouseEntered
        Color clr = new Color(0,51,51);
        insertPanel.setBackground(clr);
    }//GEN-LAST:event_lbl_insertMouseEntered

    private void lbl_insertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertMouseExited
        Color clr = new Color(0,102,102);
        insertPanel.setBackground(clr);
    }//GEN-LAST:event_lbl_insertMouseExited

    private void lbl_deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_deleteMouseEntered
        Color clr = new Color(0,51,51);
        deletePanel.setBackground(clr);
    }//GEN-LAST:event_lbl_deleteMouseEntered

    private void lbl_deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_deleteMouseExited
        Color clr = new Color(0,102,102);
        deletePanel.setBackground(clr);
    }//GEN-LAST:event_lbl_deleteMouseExited

    private void lbl_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_insertMouseClicked
        insertCourses insert = new insertCourses();
        insert.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_insertMouseClicked

    private void lbl_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_deleteMouseClicked
        deleteCourses delete = new deleteCourses();
        delete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_deleteMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AdminPage admin = new AdminPage();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(InsertDeletePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertDeletePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertDeletePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertDeletePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertDeletePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel deletePanel;
    private javax.swing.JPanel insertPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_delete;
    private javax.swing.JLabel lbl_insert;
    // End of variables declaration//GEN-END:variables
}

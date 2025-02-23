
package CourseReg;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DeleteOutline extends javax.swing.JFrame {

    
    public DeleteOutline() {
        initComponents();
    }
    
    private void deleteOutline() {
        String courseCode = txt_coursecode.getText().trim();
        
        // Validate input
        if (courseCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a course code", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Delete course " + courseCode + "?",
        "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
            
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "DELETE FROM course WHERE course_code = ? ")) {
            
            // Show processing cursor
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            pstmt.setString(1, courseCode);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Course deleted successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
                // Clear the field after successful deletion
                txt_coursecode.setText("");
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Course not found with code: " + courseCode, 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Database error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            // Reset cursor
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_loadoutline = new javax.swing.JButton();
        txt_coursecode = new javax.swing.JTextField();
        btn_insert = new javax.swing.JButton();
        btn_back1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DELETE COURSE OUTLINE");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 440, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 180));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_loadoutline.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_loadoutline.setText("DELETE COURSE OUTLINE");
        btn_loadoutline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadoutlineActionPerformed(evt);
            }
        });
        jPanel2.add(btn_loadoutline, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 330, 30));

        txt_coursecode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_coursecode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_coursecodeActionPerformed(evt);
            }
        });
        jPanel2.add(txt_coursecode, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 210, 30));

        btn_insert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_insert.setText("INSERT");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });
        jPanel2.add(btn_insert, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 292, 110, 30));

        btn_back1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_back1.setText("BACK");
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });
        jPanel2.add(btn_back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 302, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("COURSE CODE :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 140, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 1100, 380));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loadoutlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadoutlineActionPerformed
        deleteOutline();
    }//GEN-LAST:event_btn_loadoutlineActionPerformed

    private void txt_coursecodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_coursecodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_coursecodeActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        InsertOutline insert = new InsertOutline();
        insert.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        InsertDeleteOutline back = new InsertDeleteOutline();
        back.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_back1ActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DeleteOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteOutline().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back1;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_loadoutline;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_coursecode;
    // End of variables declaration//GEN-END:variables
}

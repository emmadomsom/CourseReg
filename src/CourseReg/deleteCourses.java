
package CourseReg;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class deleteCourses extends javax.swing.JFrame {
    
    
    public deleteCourses() {
        initComponents();
    }
    
    private void deleteCourseData(){
        String courseCode = txt_coursecode.getText();
        String session = combo_session.getSelectedItem().toString();
        String level = combo_level.getSelectedItem().toString();
        String semester = combo_semester.getSelectedItem().toString();

        if (courseCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Course Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "Emmadom1234@");
             
            String sql = "DELETE FROM courses WHERE course_code = ? AND session = ? AND level = ? AND semester = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, courseCode);
            pstmt.setString(2, session);
            pstmt.setString(3, level);
            pstmt.setString(4, semester);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Course deleted successfully!");
            }else{
               JOptionPane.showMessageDialog(this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE); 
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error deleting course: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }finally {
           try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close(); 
           }catch(SQLException ex){
               ex.printStackTrace();
           }
        
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_ccode = new javax.swing.JLabel();
        txt_coursecode = new javax.swing.JTextField();
        combo_session = new javax.swing.JComboBox<>();
        lbl_ccode1 = new javax.swing.JLabel();
        combo_level = new javax.swing.JComboBox<>();
        combo_semester = new javax.swing.JComboBox<>();
        lbl_ccode2 = new javax.swing.JLabel();
        lbl_ccode3 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DELETE COURSES");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 330, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 180));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_ccode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_ccode.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ccode.setText("SESSION");
        jPanel2.add(lbl_ccode, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 150, 20));

        txt_coursecode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_coursecodeActionPerformed(evt);
            }
        });
        jPanel2.add(txt_coursecode, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 450, 30));

        combo_session.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024/2025", "2023/2024", "2022/2023", "2021/2022", "2020/2021" }));
        jPanel2.add(combo_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 450, 30));

        lbl_ccode1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_ccode1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ccode1.setText("COURSE CODE");
        jPanel2.add(lbl_ccode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 150, 20));

        combo_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100 LEVEL", "200 LEVEL", "300 LEVEL", "400 LEVEL" }));
        combo_level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_levelActionPerformed(evt);
            }
        });
        jPanel2.add(combo_level, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 450, 30));

        combo_semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRST SEMESTER", "SECOND SEMESTER" }));
        combo_semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_semesterActionPerformed(evt);
            }
        });
        jPanel2.add(combo_semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 450, 30));

        lbl_ccode2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_ccode2.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ccode2.setText("LEVEL");
        jPanel2.add(lbl_ccode2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 60, 20));

        lbl_ccode3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_ccode3.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ccode3.setText("SEMESTER");
        jPanel2.add(lbl_ccode3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 150, 20));

        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel2.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 500, 140, 30));

        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel2.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 500, 140, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 840, 570));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void combo_levelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_levelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_levelActionPerformed

    private void combo_semesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_semesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_semesterActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        InsertDeletePage insert = new InsertDeletePage();
        insert.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        deleteCourseData();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void txt_coursecodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_coursecodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_coursecodeActionPerformed

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
            java.util.logging.Logger.getLogger(deleteCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(deleteCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(deleteCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(deleteCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new deleteCourses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_delete;
    private javax.swing.JComboBox<String> combo_level;
    private javax.swing.JComboBox<String> combo_semester;
    private javax.swing.JComboBox<String> combo_session;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_ccode;
    private javax.swing.JLabel lbl_ccode1;
    private javax.swing.JLabel lbl_ccode2;
    private javax.swing.JLabel lbl_ccode3;
    private javax.swing.JTextField txt_coursecode;
    // End of variables declaration//GEN-END:variables
}

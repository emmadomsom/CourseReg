
package CourseReg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.Cursor;

class DatabaseConnection{
    private static final String URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USERNAME = "root";    
    private static final String PASSWORD = "Emmadom1234@";        
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
}

public class InsertOutline extends javax.swing.JFrame {

    
    public InsertOutline() {
        initComponents();
    }
    
    private void insertOutline(){
        String courseCode = txt_coursecode.getText().trim();
        String courseName = txt_coursename.getText().trim();
        String courseOutline = txt_courseoutline.getText().trim();
        String level = combo_level.getSelectedItem().toString();
        String session = combo_session.getSelectedItem().toString();
        String semester = combo_semester.getSelectedItem().toString();
        
        if (courseCode.isEmpty() || courseName.isEmpty() || courseOutline.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in all fields", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (courseCode.length() > 10) { // adjust number based on your database column size
        JOptionPane.showMessageDialog(this,
            "Course code is too long (maximum 10 characters)",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
        }
        try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(
             "INSERT INTO course (course_code, course_name, course_outline, level,"
                     + " session, semester) VALUES (?, ?, ?, ?, ?, ?)")) {
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            pstmt.setString(1, courseCode);
            pstmt.setString(2, courseName);
            pstmt.setString(3, courseOutline);
            pstmt.setString(4, level);
            pstmt.setString(5, session);
            pstmt.setString(6, semester);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Course outline inserted successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
            } 
            
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            handleSQLException(ex);
        }finally{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void clearFields() {
    txt_coursecode.setText("");
    txt_coursename.setText("");
    txt_courseoutline.setText("");
    }
    
    private void handleSQLException(SQLException ex) {
    if (ex.getMessage().contains("duplicate")) {
        JOptionPane.showMessageDialog(this, "Course code already exists!", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_coursename = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_courseoutline = new javax.swing.JTextArea();
        btn_loadoutline = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_coursecode = new javax.swing.JTextField();
        btn_back = new javax.swing.JButton();
        combo_level = new javax.swing.JComboBox<>();
        combo_session = new javax.swing.JComboBox<>();
        combo_semester = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INSERT COURSE OUTLINE");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 440, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 180));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("COURSE OUTLINE :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 170, 20));

        txt_coursename.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_coursename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_coursenameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 400, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("COURSE NAME :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 140, 20));

        txt_courseoutline.setColumns(20);
        txt_courseoutline.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_courseoutline.setRows(5);
        jScrollPane1.setViewportView(txt_courseoutline);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 910, 240));

        btn_loadoutline.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_loadoutline.setText("INSERT COURSE OUTLINE");
        btn_loadoutline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadoutlineActionPerformed(evt);
            }
        });
        jPanel2.add(btn_loadoutline, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 230, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("COURSE CODE :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 140, 20));

        txt_coursecode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_coursecode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_coursecodeActionPerformed(evt);
            }
        });
        jPanel2.add(txt_coursecode, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 210, 30));

        btn_back.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel2.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 90, -1));

        combo_level.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100 LEVEL", "200 LEVEL", "300 LEVEL", "400 LEVEL" }));
        combo_level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_levelActionPerformed(evt);
            }
        });
        jPanel2.add(combo_level, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 170, -1));

        combo_session.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_session.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024/2025", "2023/2024", "2022/2023", "2021/2022", "2020/2021" }));
        combo_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_sessionActionPerformed(evt);
            }
        });
        jPanel2.add(combo_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 190, -1));

        combo_semester.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRST SEMESTER", "SECOND SEMESTER" }));
        combo_semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_semesterActionPerformed(evt);
            }
        });
        jPanel2.add(combo_semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 250, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 1080, 530));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_coursenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_coursenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_coursenameActionPerformed

    private void btn_loadoutlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadoutlineActionPerformed
        insertOutline();

    }//GEN-LAST:event_btn_loadoutlineActionPerformed

    private void txt_coursecodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_coursecodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_coursecodeActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        InsertDeleteOutline back = new InsertDeleteOutline();
        back.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void combo_levelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_levelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_levelActionPerformed

    private void combo_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sessionActionPerformed

    }//GEN-LAST:event_combo_sessionActionPerformed

    private void combo_semesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_semesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_semesterActionPerformed

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
            java.util.logging.Logger.getLogger(InsertOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertOutline().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_loadoutline;
    private javax.swing.JComboBox<String> combo_level;
    private javax.swing.JComboBox<String> combo_semester;
    private javax.swing.JComboBox<String> combo_session;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_coursecode;
    private javax.swing.JTextField txt_coursename;
    private javax.swing.JTextArea txt_courseoutline;
    // End of variables declaration//GEN-END:variables
}

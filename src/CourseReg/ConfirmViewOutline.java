
package CourseReg;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConfirmViewOutline extends javax.swing.JFrame {
    private ResultSet courses = null;
    private Connection conn = null;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USER = "root";
    private static final String PASS = "Emmadom1234@";

    
    public ConfirmViewOutline() {
        initComponents();
    }
    
    private void loadCourses(){
        String level = combo_level.getSelectedItem().toString();
        String session = combo_session.getSelectedItem().toString();
        String semester = combo_semester.getSelectedItem().toString();
        Connection conn = null;
        ResultSet rs = null;
        
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT course_code, course_name, course_outline FROM course " +
                     "WHERE level = ? AND session = ? AND semester = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, level);
            pstmt.setString(2, session);
            pstmt.setString(3, semester);
            
            rs = pstmt.executeQuery();
            java.util.List<Object[]> courseData = new java.util.ArrayList<>();
            
            while(rs.next()) {
                Object[] row = {
                rs.getString("course_code"),
                rs.getString("course_name"),
                rs.getString("course_outline")
            }; 
                courseData.add(row);
            }
            if (!courseData.isEmpty()) {
            ViewOutline viewOutline = new ViewOutline(courseData);
            viewOutline.setVisible(true);
            this.dispose();
            } else {
            JOptionPane.showMessageDialog(this, 
                "No courses found for:\nLevel: " + level + 
                "\nSession: " + session + 
                "\nSemester: " + semester);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }finally{
            try{
                if(rs != null) rs.close();
                if(conn != null) conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        combo_semester = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        combo_level = new javax.swing.JComboBox<>();
        btn_back = new javax.swing.JButton();
        btn_load = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        combo_session = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        combo_semester1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        combo_level1 = new javax.swing.JComboBox<>();
        btn_back1 = new javax.swing.JButton();
        btn_load1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        combo_session1 = new javax.swing.JComboBox<>();

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combo_semester.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRST SEMESTER", "SECOND SEMESTER" }));
        combo_semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_semesterActionPerformed(evt);
            }
        });
        jPanel2.add(combo_semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 380, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SEMESTER");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 80, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LEVEL");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 80, -1));

        combo_level.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100 LEVEL", "200 LEVEL", "300 LEVEL", "400 LEVEL" }));
        combo_level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_levelActionPerformed(evt);
            }
        });
        jPanel2.add(combo_level, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 380, -1));

        btn_back.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel2.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 110, -1));

        btn_load.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_load.setText("LOAD COURSES");
        btn_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadActionPerformed(evt);
            }
        });
        jPanel2.add(btn_load, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 170, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SESSION");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 80, -1));

        combo_session.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_session.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024/2025", "2023/2024", "2022/2023", "2021/2022", "2020/2021" }));
        combo_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_sessionActionPerformed(evt);
            }
        });
        jPanel2.add(combo_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 380, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CONFIRM COURSE OUTLINE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 460, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 150));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combo_semester1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_semester1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRST SEMESTER", "SECOND SEMESTER" }));
        combo_semester1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_semester1ActionPerformed(evt);
            }
        });
        jPanel3.add(combo_semester1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 380, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SEMESTER");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 80, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("LEVEL");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 80, -1));

        combo_level1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_level1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100 LEVEL", "200 LEVEL", "300 LEVEL", "400 LEVEL" }));
        combo_level1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_level1ActionPerformed(evt);
            }
        });
        jPanel3.add(combo_level1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 380, -1));

        btn_back1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_back1.setText("BACK");
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });
        jPanel3.add(btn_back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 110, -1));

        btn_load1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_load1.setText("LOAD COURSES OUTLINE");
        btn_load1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_load1ActionPerformed(evt);
            }
        });
        jPanel3.add(btn_load1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 210, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SESSION");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 80, -1));

        combo_session1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_session1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024/2025", "2023/2024", "2022/2023", "2021/2022", "2020/2021" }));
        combo_session1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_session1ActionPerformed(evt);
            }
        });
        jPanel3.add(combo_session1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 380, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 670, 470));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void combo_semesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_semesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_semesterActionPerformed

    private void combo_levelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_levelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_levelActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        Registration reg = new Registration();
        reg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadActionPerformed
        

    }//GEN-LAST:event_btn_loadActionPerformed

    private void combo_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sessionActionPerformed

    }//GEN-LAST:event_combo_sessionActionPerformed

    private void combo_semester1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_semester1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_semester1ActionPerformed

    private void combo_level1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_level1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_level1ActionPerformed

    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        AdminPage admin = new AdminPage();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_back1ActionPerformed

    private void btn_load1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_load1ActionPerformed
        loadCourses();
    }//GEN-LAST:event_btn_load1ActionPerformed

    private void combo_session1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_session1ActionPerformed

    }//GEN-LAST:event_combo_session1ActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfirmViewOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfirmViewOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfirmViewOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmViewOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfirmViewOutline().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_back1;
    private javax.swing.JButton btn_load;
    private javax.swing.JButton btn_load1;
    private javax.swing.JComboBox<String> combo_level;
    private javax.swing.JComboBox<String> combo_level1;
    private javax.swing.JComboBox<String> combo_semester;
    private javax.swing.JComboBox<String> combo_semester1;
    private javax.swing.JComboBox<String> combo_session;
    private javax.swing.JComboBox<String> combo_session1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}

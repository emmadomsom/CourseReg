
package CourseReg;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class ConfimPage extends javax.swing.JFrame {
    private ResultSet courses = null;
    private Connection conn = null;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USER = "root";
    private static final String PASS = "Emmadom1234@";

 
    public ConfimPage() {
        initComponents();
        
    }
    
    private boolean validateStudentLevel(String selectedLevel) {
        String studentLevel = UserSession.getLevel(); // Assuming this method exists in UserSession
        
        if (!selectedLevel.equals(studentLevel)) {
            JOptionPane.showMessageDialog(this,
                "You can only register for courses in your current level (" + studentLevel + ").",
                "Invalid Level Selection",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean loadCourses(){
        String level = combo_level.getSelectedItem().toString();
        String session = combo_session.getSelectedItem().toString();
        String semester = combo_semester.getSelectedItem().toString();
        
        if (!validateStudentLevel(level)) {
            return false;
        }
        
         if (!validateRegistrationPeriod(session, semester)) {
            return false;
        }
        
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            String checkRegistrationQuery = "SELECT COUNT(*) as registration_count " +
            "FROM registrations " +
            "WHERE reg_no = ? AND level = ? AND session = ? AND semester = ?";
            
            PreparedStatement checkStmt = conn.prepareStatement(checkRegistrationQuery);
            String regno = UserSession.getRegNo();
            checkStmt.setString(1, regno);
            checkStmt.setString(2, level);
            checkStmt.setString(3, session);
            checkStmt.setString(4, semester);
        
        ResultSet checkRs = checkStmt.executeQuery();
        
        if (checkRs.next() && checkRs.getInt("registration_count") > 0) {
            JOptionPane.showMessageDialog(this, 
                "You have already registered for courses in this " + 
                level + ", " + session + ", " + semester + " criteria.",
                "Registration Exists",
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
            
            String sql = "SELECT course_code, course_name, unit_load FROM courses WHERE level = ? AND session = ? AND semester = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, level);
            pstmt.setString(2, session);
            pstmt.setString(3,semester);
            
            courses = pstmt.executeQuery();
            
            if (!courses.isBeforeFirst()) {  // No data
            JOptionPane.showMessageDialog(this, "No courses found for the selected criteria.");
            return false;
            }
            
            int rowCount = 0;
            while (courses.next()) {
            rowCount++;
            System.out.println("Found course: " + 
                courses.getString("course_code") + " - " + 
                courses.getString("course_name"));
            }
            System.out.println("Total courses found: " + rowCount);
            
            courses.beforeFirst();
            
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Courses loaded successfully.");
                return true;
            }else{
                JOptionPane.showMessageDialog(this, "No courses found for the selected criteria.");
                return false;
            }
            
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetchisng courses: " + e.getMessage());
            return false;
        }  
    }
    private boolean validateRegistrationPeriod(String session, String semester) {
    if (!RegistrationSettings.getInstance().isValidRegistrationPeriod(session, semester)) {
        JOptionPane.showMessageDialog(this,
            "Course registration for the selected criteria is not open.\n" +
            "Please contact the administrator.",
            "Registration Closed",
            JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
}
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        combo_semester = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        combo_level = new javax.swing.JComboBox<>();
        btn_back = new javax.swing.JButton();
        btn_load = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        combo_session = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CONFIRM PAGE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 260, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 180));

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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 670, 470));

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
       String selectedSession = combo_session.getSelectedItem().toString();
        String selectedSemester = combo_semester.getSelectedItem().toString();
        
        if (!validateRegistrationPeriod(selectedSession, selectedSemester)) {
            return;
        }
        
        if(loadCourses()){
           try{
               RegPage regPage = new RegPage(
                   combo_level.getSelectedItem().toString(),
                   selectedSession,
                   selectedSemester
                   
           );
           regPage.setCourses(courses);
           regPage.setVisible(true);
           this.dispose();
           }catch(Exception e){
              e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error opening registration page: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE); 
           }
        }
 
    }//GEN-LAST:event_btn_loadActionPerformed

    private void combo_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sessionActionPerformed
       
    }//GEN-LAST:event_combo_sessionActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfimPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfimPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfimPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfimPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_load;
    private javax.swing.JComboBox<String> combo_level;
    private javax.swing.JComboBox<String> combo_semester;
    private javax.swing.JComboBox<String> combo_session;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}

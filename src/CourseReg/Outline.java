
package CourseReg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class Outline extends javax.swing.JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USER = "root";
    private static final String PASS = "Emmadom1234@";
    
    public Outline() {
        initComponents();
        
        txt_coursename.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        
        txt_courseoutline.setEditable(false);
        SimpleAttributeSet centerAlign = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAlign, StyleConstants.ALIGN_CENTER);
        txt_courseoutline.setDocument(new javax.swing.text.DefaultStyledDocument());
        javax.swing.text.StyledDocument doc = (javax.swing.text.StyledDocument) txt_courseoutline.getDocument();
        doc.setParagraphAttributes(0, doc.getLength(), centerAlign, false);
    }
    
    public class CourseData{
        private String courseName;
        private String courseOutline;
        
        public CourseData(String courseName, String courseOutline) {
        this.courseName = courseName;
        this.courseOutline = courseOutline;
        }
        
        public String getCourseName() { return courseName; }
        public String getCourseOutline() { return courseOutline; }
    }
    
    private Connection getConnection(){
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public CourseData getCourseOutline(String courseCode){
        String courseOutline = "Course not found!";
        String courseName = "";
        String query = "SELECT course_name, course_outline FROM course WHERE course_code = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, courseCode);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                courseName = rs.getString("course_name");
                courseOutline = rs.getString("course_outline");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            courseOutline = "Error retrieving course outline: " + e.getMessage();
        }
        return new CourseData(courseName, courseOutline);

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_coursename = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_courseoutline = new javax.swing.JTextArea();
        btn_loadoutline = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_coursecode = new javax.swing.JTextField();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COURSE OUTLINE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 300, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 180));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("COURSE OUTLINE :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 170, 20));

        txt_coursename.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_coursename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_coursenameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 720, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("COURSE NAME :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 140, 20));

        txt_courseoutline.setColumns(20);
        txt_courseoutline.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_courseoutline.setRows(5);
        jScrollPane1.setViewportView(txt_courseoutline);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 870, 240));

        btn_loadoutline.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_loadoutline.setText("LOAD COURSE OUTLINE");
        btn_loadoutline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadoutlineActionPerformed(evt);
            }
        });
        jPanel2.add(btn_loadoutline, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 230, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("COURSE CODE :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 140, 20));

        txt_coursecode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_coursecode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_coursecodeActionPerformed(evt);
            }
        });
        jPanel2.add(txt_coursecode, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 210, 30));

        btn_back.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel2.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 90, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 1080, 530));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loadoutlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadoutlineActionPerformed
       String courseCode = txt_coursecode.getText().trim();
       CourseData courseData = getCourseOutline(courseCode);
       txt_coursename.setText(courseData.getCourseName());

       txt_courseoutline.setText(courseData.getCourseOutline());
       
    }//GEN-LAST:event_btn_loadoutlineActionPerformed

    private void txt_coursenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_coursenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_coursenameActionPerformed

    private void txt_coursecodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_coursecodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_coursecodeActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        HomePage back = new HomePage();
        back.setVisible(true);
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
            java.util.logging.Logger.getLogger(Outline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Outline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Outline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Outline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Outline().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_loadoutline;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_coursecode;
    private javax.swing.JTextField txt_coursename;
    private javax.swing.JTextArea txt_courseoutline;
    // End of variables declaration//GEN-END:variables
}

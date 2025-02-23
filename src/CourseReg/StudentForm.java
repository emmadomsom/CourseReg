
package CourseReg;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;


public class StudentForm extends javax.swing.JFrame {

    
    public StudentForm() {
        initComponents();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Database driver not found: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void registerStudent(){
        String name = txt_name.getText().trim();
    String regNo = txt_regno.getText().trim();
    String nationality = txt_nationality.getText().trim();
    String gender = combo_gender.getSelectedItem().toString();
    String contact = txt_contact.getText().trim();
    String email = txt_email.getText().trim();
    String faculty = txt_faculty.getText().trim();
    String department = txt_department.getText().trim();
    String homeAddress = txt_homeaddress.getText().trim();
    String city = txt_city.getText().trim();
    String state = txt_state.getText().trim();
    String country = txt_country.getText().trim();
    String guardianName = txt_guardname.getText().trim();
    String guardianContact = txt_guardcontact.getText().trim();
    String relationship = combo_relationship.getSelectedItem().toString();
    String modeOfEntry = jComboBox1.getSelectedItem().toString();
    
    java.util.Date dob = DateChooser_dob.getDate();
    java.sql.Date sqlDOB = null;
    if (dob != null) {
        sqlDOB = new java.sql.Date(dob.getTime());
    }
    
    if (!isValidRegNo(regNo)) {
        JOptionPane.showMessageDialog(this, 
            "Registration number must be in format YYYY/XXXXXX\n" +
            "Example: 2021/245883",
            "Validation Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    if (name.isEmpty() || regNo.isEmpty() || nationality.isEmpty() || 
        contact.isEmpty() || email.isEmpty() || faculty.isEmpty() || 
        department.isEmpty() || dob == null) {
        JOptionPane.showMessageDialog(this, 
            "Please fill in all required fields", 
            "Validation Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        JOptionPane.showMessageDialog(this, 
            "Please enter a valid email address", 
            "Validation Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!isValidPhoneNumber(contact)) {
    JOptionPane.showMessageDialog(this, 
        "Please enter a valid contact number", 
        "Validation Error", 
        JOptionPane.ERROR_MESSAGE);
    return;
    }
    
    if (!isValidPhoneNumber(guardianContact)) {
    JOptionPane.showMessageDialog(this, 
        "Please enter a valid guardian contact number", 
        "Validation Error", 
        JOptionPane.ERROR_MESSAGE);
    return;
}

if (dob != null && !isValidAge(dob)) {
    JOptionPane.showMessageDialog(this, 
        "Student must be between 16 and 100 years old", 
        "Validation Error", 
        JOptionPane.ERROR_MESSAGE);
    return;
}
    
     String url = "jdbc:mysql://localhost:3306/user_db";
    String user = "root";
    String password = "Emmadom1234@";
    
    Connection conn = null;
    PreparedStatement checkStmt = null;
    PreparedStatement pstmt = null;
    
    try {
        conn = DriverManager.getConnection(url, user, password);
        
        String checkQuery = "SELECT COUNT(*) FROM (SELECT reg_no FROM student_details " +
                          "UNION SELECT reg_no FROM pending_student_registrations) combined " +
                          "WHERE reg_no = ?";
        checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, regNo);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this,
                    "A student with this registration number already exists",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        
        String sql = "INSERT INTO pending_student_registrations (name, reg_no, nationality, " +
                    "gender, contact_number, email, faculty, department, dob, home_address, " +
                    "city, state, country, guardian_name, guardian_contact, relationship, " +
                    "mode_of_entry, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'PENDING')";
        
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, regNo);
        pstmt.setString(3, nationality);
        pstmt.setString(4, gender);
        pstmt.setString(5, contact);
        pstmt.setString(6, email);
        pstmt.setString(7, faculty);
        pstmt.setString(8, department);
        pstmt.setDate(9, sqlDOB);
        pstmt.setString(10, homeAddress);
        pstmt.setString(11, city);
        pstmt.setString(12, state);
        pstmt.setString(13, country);
        pstmt.setString(14, guardianName);
        pstmt.setString(15, guardianContact);
        pstmt.setString(16, relationship);
        pstmt.setString(17, modeOfEntry);
        
        
        int rowsAffected = pstmt.executeUpdate();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this,
                "Registration submitted successfully! Waiting for admin approval.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
                
            // Clear all fields after successful registration
            clearFields();
        }else{
            JOptionPane.showMessageDialog(this,
                "Failed to register student details",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }catch(SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Database Error: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
    } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (checkStmt != null) checkStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
  }
    
    private void clearFields() {
    txt_name.setText("");
    txt_regno.setText("");
    txt_nationality.setText("");
    txt_contact.setText("");
    txt_email.setText("");
    txt_faculty.setText("");
    txt_department.setText("");
    txt_homeaddress.setText("");
    txt_city.setText("");
    txt_state.setText("");
    txt_country.setText("");
    txt_guardname.setText("");
    txt_guardcontact.setText("");
    DateChooser_dob.setDate(null);
    combo_gender.setSelectedIndex(0);
    combo_relationship.setSelectedIndex(0);
    jComboBox1.setSelectedIndex(0);
}
    private boolean isValidPhoneNumber(String phone) {
    // Add your phone number validation regex
    return phone.matches("^\\+?[0-9]{10,15}$");
}
    private boolean isValidAge(Date dob) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dob);
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if (now.get(Calendar.MONTH) < cal.get(Calendar.MONTH) || 
            (now.get(Calendar.MONTH) == cal.get(Calendar.MONTH) && 
             now.get(Calendar.DAY_OF_MONTH) < cal.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age >= 16 && age <= 100;
    }
    
    private boolean isValidRegNo(String regNo) {
    // Regular expression for the format YYYY/XXXXXX where X is any digit
    String regex = "^\\d{4}/\\d{6}$";
    
    if (!regNo.matches(regex)) {
        return false;
    }
    
    // Extract the year part
    String yearStr = regNo.substring(0, 4);
    int year = Integer.parseInt(yearStr);
    
    // Check if year is reasonable (e.g., not future year and not too old)
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    return year <= currentYear && year >= (currentYear - 100);
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nationality = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        DateChooser_dob = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_regno = new javax.swing.JTextField();
        txt_faculty = new javax.swing.JTextField();
        combo_gender = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txt_contact = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txt_email = new javax.swing.JTextField();
        txt_department = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_guardcontact = new javax.swing.JTextField();
        txt_guardname = new javax.swing.JTextField();
        txt_homeaddress = new javax.swing.JTextField();
        txt_city = new javax.swing.JTextField();
        txt_state = new javax.swing.JTextField();
        txt_country = new javax.swing.JTextField();
        combo_relationship = new javax.swing.JComboBox<>();
        btn_submit = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("STUDENT PERSONAL DETAILS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 200));

        jLabel1.setText("DEPARTMENT :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 500, 120, 30));

        jLabel3.setText("EMAIL :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 410, 80, 30));
        getContentPane().add(txt_nationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 250, 230, 30));

        jLabel4.setText("NAME OF STUDENT: ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, 30));
        getContentPane().add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 380, 40));
        getContentPane().add(DateChooser_dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 220, 30));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 1590, 10));

        jLabel5.setText("REGISTRATION NUMBER:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, 30));

        jLabel6.setText("NATIONALITY :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 250, 90, 30));

        jLabel7.setText("GENDER :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 300, 70, 30));
        getContentPane().add(txt_regno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 272, 190, 30));

        txt_faculty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_facultyActionPerformed(evt);
            }
        });
        getContentPane().add(txt_faculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 500, 230, 30));

        combo_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE" }));
        getContentPane().add(combo_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 300, 160, 30));

        jLabel8.setText("CONTACT NUMBER :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 360, 120, 30));
        getContentPane().add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 360, 230, 30));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1590, 10));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1590, 10));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1590, 10));

        jPanel7.setBackground(new java.awt.Color(0, 102, 102));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(0, 102, 102));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1590, 10));

        jPanel9.setBackground(new java.awt.Color(0, 102, 102));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(0, 102, 102));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1590, 10));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1590, 10));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1590, 10));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 1590, 10));

        jLabel9.setText("D.O.B :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, 30));

        jLabel10.setText("COUNTRY  OF RESIDENCE:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 640, 160, 30));

        jLabel11.setText("FACULTY :");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 150, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UTME", "DIRECT ENTRY", "TRANSFER" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 180, 30));
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 410, 230, 30));
        getContentPane().add(txt_department, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 500, 230, 30));

        jLabel12.setText("MODE OF ENTRY :");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 110, 30));

        jLabel13.setText("GUARDIANS CONTACT :");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 720, 140, 30));

        jLabel14.setText("CITY :");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, 110, 30));

        jLabel15.setText("STATE/PROVINCE :");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 590, 110, 30));

        jPanel11.setBackground(new java.awt.Color(0, 102, 102));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 690, 1590, 10));

        jLabel16.setText("HOME ADDRESS : ");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 110, 30));

        jLabel17.setText("GUARDIANS NAME:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 720, 110, 30));

        jLabel18.setText("RELATIONSHIP :");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 770, 110, 30));

        txt_guardcontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_guardcontactActionPerformed(evt);
            }
        });
        getContentPane().add(txt_guardcontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 720, 170, 30));

        txt_guardname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_guardnameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_guardname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 720, 360, 30));

        txt_homeaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_homeaddressActionPerformed(evt);
            }
        });
        getContentPane().add(txt_homeaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 590, 360, 30));

        txt_city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cityActionPerformed(evt);
            }
        });
        getContentPane().add(txt_city, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 650, 170, 30));

        txt_state.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stateActionPerformed(evt);
            }
        });
        getContentPane().add(txt_state, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 590, 170, 30));

        txt_country.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_countryActionPerformed(evt);
            }
        });
        getContentPane().add(txt_country, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 640, 170, 30));

        combo_relationship.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FATHER", "MOTHER", "BROTHER", "SISTER", "AUNT", "UNCLE", " " }));
        combo_relationship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_relationshipActionPerformed(evt);
            }
        });
        getContentPane().add(combo_relationship, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 770, 220, 40));

        btn_submit.setBackground(new java.awt.Color(0, 102, 102));
        btn_submit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_submit.setForeground(new java.awt.Color(255, 255, 255));
        btn_submit.setText("SUBMIT");
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });
        getContentPane().add(btn_submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 783, 420, 40));

        btn_back.setBackground(new java.awt.Color(0, 102, 102));
        btn_back.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 790, 140, 30));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_facultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_facultyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_facultyActionPerformed

    private void txt_guardcontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_guardcontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_guardcontactActionPerformed

    private void txt_guardnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_guardnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_guardnameActionPerformed

    private void txt_homeaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_homeaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_homeaddressActionPerformed

    private void txt_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cityActionPerformed

    private void txt_stateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stateActionPerformed

    private void txt_countryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_countryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_countryActionPerformed

    private void combo_relationshipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_relationshipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_relationshipActionPerformed

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        registerStudent();
    }//GEN-LAST:event_btn_submitActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        FirstPage first = new FirstPage();
        first.setVisible(true);
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
            java.util.logging.Logger.getLogger(StudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooser_dob;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_submit;
    private javax.swing.JComboBox<String> combo_gender;
    private javax.swing.JComboBox<String> combo_relationship;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField txt_city;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_country;
    private javax.swing.JTextField txt_department;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_faculty;
    private javax.swing.JTextField txt_guardcontact;
    private javax.swing.JTextField txt_guardname;
    private javax.swing.JTextField txt_homeaddress;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_nationality;
    private javax.swing.JTextField txt_regno;
    private javax.swing.JTextField txt_state;
    // End of variables declaration//GEN-END:variables
}

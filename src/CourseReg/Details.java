
package CourseReg;

import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JComponent;


public class Details extends javax.swing.JFrame {
    private String loggedInRegNo;
    private Connection connection;
    
    private static final String URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Emmadom1234@";

    
    public Details() {
        initComponents();
        this.loggedInRegNo = UserSession.getRegNo();
        establishConnection();
        setFieldsReadOnly();
        loadStudentDetails();
    }
    
    private void establishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Connection Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
     private void setFieldsReadOnly() {
        // Make all text fields uneditable
        txt_regno.setEditable(false);
        txt_name.setEditable(false);
        txt_email.setEditable(false);
        txt_nationality.setEditable(false);
        txt_contact.setEditable(false);
        txt_department.setEditable(false);
        txt_faculty.setEditable(false);
        txt_guardianame.setEditable(false);
        txt_homeaddress.setEditable(false);
        txt_city.setEditable(false);
        txt_state.setEditable(false);
        txt_countryofresidence.setEditable(false);
        
        // Disable editable components
        datechooser_dob.setEnabled(false);
        combo_gender.setEnabled(false);
        combo_relationship.setEnabled(false);
        jComboBox1.setEnabled(false);
    }
     
     private void loadStudentDetails() {
        try {
            if (connection == null || connection.isClosed()) {
                establishConnection();
            }
            
            String query = "SELECT * FROM student_details WHERE reg_no = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, loggedInRegNo);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Set values to the form fields
                txt_regno.setText(rs.getString("reg_no"));
                txt_name.setText(rs.getString("name"));
                txt_email.setText(rs.getString("email"));
                txt_nationality.setText(rs.getString("nationality"));
                txt_contact.setText(rs.getString("contact_number"));
                txt_department.setText(rs.getString("department"));
                txt_faculty.setText(rs.getString("faculty"));
                txt_guardianame.setText(rs.getString("guardian_name"));
                txt_homeaddress.setText(rs.getString("home_address"));
                txt_city.setText(rs.getString("city"));
                txt_state.setText(rs.getString("state"));
                txt_countryofresidence.setText(rs.getString("country"));
                
                // Set date of birth
                if (rs.getDate("dob") != null) {
                    datechooser_dob.setDate(rs.getDate("dob"));
                }
                
                // Set selected items in combo boxes
                combo_gender.setSelectedItem(rs.getString("gender"));
                combo_relationship.setSelectedItem(rs.getString("relationship"));
                jComboBox1.setSelectedItem(rs.getString("mode_of_entry"));
            } else {
                JOptionPane.showMessageDialog(this, "No student record found for Registration Number: " + loggedInRegNo);
            }
            
            rs.close();
            pst.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading student details: " + e.getMessage());
            e.printStackTrace();
        }
    }
     
     private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Override dispose to ensure connection is closed when form is closed
    @Override
    public void dispose() {
        closeConnection();
        super.dispose();
    }
    
    private void printStudentDetails() {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setJobName("Student Details - " + txt_regno.getText());
    
     PageFormat pageFormat = job.defaultPage();
    pageFormat = job.pageDialog(pageFormat);
    
    job.setPrintable(new Printable() {
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }
            
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            
            // Add a title at the top
            g2d.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
            g2d.drawString("STUDENT PERSONAL DETAILS - " + txt_name.getText(), 50, 20);
            
            // Adjust the translation to account for the title
            g2d.translate(0, 30);
            
            // Scale to fit the page while preserving aspect ratio
            double scaleX = pageFormat.getImageableWidth() / detailsPanel.getWidth();
            double scaleY = (pageFormat.getImageableHeight() - 30) / detailsPanel.getHeight();
            double scale = Math.min(scaleX, scaleY);
            g2d.scale(scale, scale);
            boolean wasBuffered = disableDoubleBuffering(detailsPanel);
            
            // Print the panel
            detailsPanel.paint(g2d);
            
            // Restore double buffering
            restoreDoubleBuffering(detailsPanel, wasBuffered);
            
            return Printable.PAGE_EXISTS;
        }
    });
    
    boolean doPrint = job.printDialog();
    if (doPrint) {
        try {
            job.print();
            JOptionPane.showMessageDialog(this, "Printing completed successfully!");
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage(), 
                                         "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    private boolean disableDoubleBuffering(Component c) {
    if (c instanceof JComponent) {
        JComponent jc = (JComponent) c;
        boolean wasBuffered = jc.isDoubleBuffered();
        jc.setDoubleBuffered(false);
        return wasBuffered;
    }
    return false;
}

private void restoreDoubleBuffering(Component c, boolean wasBuffered) {
    if (c instanceof JComponent) {
        ((JComponent) c).setDoubleBuffered(wasBuffered);
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        detailsPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_regno = new javax.swing.JTextField();
        datechooser_dob = new com.toedter.calendar.JDateChooser();
        txt_name = new javax.swing.JTextField();
        txt_nationality = new javax.swing.JTextField();
        txt_contact = new javax.swing.JTextField();
        combo_gender = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txt_countryofresidence = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        txt_department = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_faculty = new javax.swing.JTextField();
        txt_guardianame = new javax.swing.JTextField();
        txt_homeaddress = new javax.swing.JTextField();
        txt_city = new javax.swing.JTextField();
        txt_state = new javax.swing.JTextField();
        btn_print = new javax.swing.JButton();
        combo_relationship = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("STUDENT PERSONAL DETAILS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 200));

        detailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("EMAIL :");
        detailsPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 190, 80, 20));

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 180, 220, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("REG NO :");
        detailsPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 80, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("DEPARTMENT :");
        detailsPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 260, 90, 20));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        detailsPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1580, 10));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("D.O.B :");
        detailsPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 50, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("NATIONALITY: ");
        detailsPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 100, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("GENDER :");
        detailsPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 90, 80, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("CONTACT NUMBER :");
        detailsPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 140, 130, 20));

        txt_regno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_regnoActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_regno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 200, 30));
        detailsPanel.add(datechooser_dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 180, 30));

        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 290, 30));

        txt_nationality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nationalityActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_nationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, 220, 30));

        txt_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contactActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 130, 220, 30));

        combo_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE ", "FEMALE" }));
        detailsPanel.add(combo_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 90, 140, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("STUDENT NAME :");
        detailsPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 110, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 102));
        jLabel12.setText("FACULTY :");
        detailsPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 90, 20));

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        detailsPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 1590, 10));

        txt_countryofresidence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_countryofresidenceActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_countryofresidence, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 550, 250, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UTME", "DIRECT ENTRY", "TRANSFER" }));
        detailsPanel.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 150, 30));

        txt_department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_departmentActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_department, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 260, 260, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 102));
        jLabel13.setText("MODE OF ENTRY :");
        detailsPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 110, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 102));
        jLabel14.setText("COUNTRY OF RESIDENCE :");
        detailsPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 550, 160, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("RELATIONSHIP :");
        detailsPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 120, 20));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        detailsPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 1590, 10));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("GUARDIAN'S NAME :");
        detailsPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 375, 120, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setText("HOME ADDRESS :");
        detailsPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 120, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setText("CITY :");
        detailsPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 120, 20));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 102));
        jLabel19.setText("STATE:");
        detailsPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 490, 120, 20));

        txt_faculty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_facultyActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_faculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, 250, 30));

        txt_guardianame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_guardianameActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_guardianame, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 250, 30));

        txt_homeaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_homeaddressActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_homeaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 250, 30));

        txt_city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cityActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_city, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, 250, 30));

        txt_state.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stateActionPerformed(evt);
            }
        });
        detailsPanel.add(txt_state, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 490, 250, 30));

        btn_print.setBackground(new java.awt.Color(0, 102, 102));
        btn_print.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setText("PRINT");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        detailsPanel.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 580, 320, 30));

        combo_relationship.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FATHER", "MOTHER", "BROTHER", "SISTER", "AUNT", "UNCLE", " " }));
        detailsPanel.add(combo_relationship, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 422, 210, 30));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        detailsPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 520, 100, 30));

        getContentPane().add(detailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1590, 700));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_regnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_regnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_regnoActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void txt_nationalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nationalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nationalityActionPerformed

    private void txt_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactActionPerformed

    private void txt_countryofresidenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_countryofresidenceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_countryofresidenceActionPerformed

    private void txt_departmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_departmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_departmentActionPerformed

    private void txt_facultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_facultyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_facultyActionPerformed

    private void txt_guardianameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_guardianameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_guardianameActionPerformed

    private void txt_homeaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_homeaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_homeaddressActionPerformed

    private void txt_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cityActionPerformed

    private void txt_stateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stateActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        detailsPanel.setPreferredSize(new Dimension(detailsPanel.getWidth(), detailsPanel.getHeight()));
        printStudentDetails();
    }//GEN-LAST:event_btn_printActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        HomePage home = new HomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox<String> combo_gender;
    private javax.swing.JComboBox<String> combo_relationship;
    private com.toedter.calendar.JDateChooser datechooser_dob;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField txt_city;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_countryofresidence;
    private javax.swing.JTextField txt_department;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_faculty;
    private javax.swing.JTextField txt_guardianame;
    private javax.swing.JTextField txt_homeaddress;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_nationality;
    private javax.swing.JTextField txt_regno;
    private javax.swing.JTextField txt_state;
    // End of variables declaration//GEN-END:variables
}

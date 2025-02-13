
package CourseReg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.print.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;



public class RegPage extends javax.swing.JFrame {
    private static final int MAX_UNITS = 15;
    private ResultSet courses;
    private String selectedLevel;
    private String selectedSession;
    private String selectedSemester;
    private Connection conn;
    private static final int PAGE_SIZE = 50;
    private int currentPage = 0;
    //private String userRegNo;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USER = "root";
    private static final String PASS = "Emmadom1234@";
    private JLabel totalUnitsLabel;
    

    
    public RegPage(String level, String session, String semester ) {
        this.selectedLevel = level;
        this.selectedSession = session;
        this.selectedSemester = semester;
        
        
        initComponents();
        setupComboBoxes();
        connectToDatabase();
        
        String regno = UserSession.getRegNo();
        txt_regno.setText(regno);
        txt_regno.setEditable(false);
        
        String name = UserSession.getName();
        txt_name.setText(name);
        txt_name.setEditable(false);
       
        totalUnitsLabel = new JLabel("Total Units: 0/" + MAX_UNITS + " units");
        totalUnitsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        totalUnitsLabel.setHorizontalAlignment(JLabel.RIGHT);
        //coursePanel.add(totalUnitsLabel, new AbsoluteConstraints(50, 300, 200, 30) );
 
    }
     public void setCourses(ResultSet courses) {
        this.courses = courses;
        setupTable(); 
    }
    
    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage());
        }
    }
    
    private void setupComboBoxes() {
        combo_level.setSelectedItem(selectedLevel);
        combo_session.setSelectedItem(selectedSession);
        combo_semester.setSelectedItem(selectedSemester);
        
        combo_level.setEnabled(false);
        combo_session.setEnabled(false);
        combo_semester.setEnabled(false);
    }
   
    
    private void setupTable() {
        try {
            if(courses == null){
                System.out.println("Courses ResultSet is null");
                return;
            }
        
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"Select", "Course Name", "Course Code", "Unit Load"}, 0) {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnIndex == 0 ? Boolean.class :
                            columnIndex == 3 ? Integer.class: String.class;
                }
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 0;
                    }
            };
            
            courses.beforeFirst();
                 while(courses.next()){
                     String courseName = courses.getString("course_name");
                     String courseCode = courses.getString("course_code");
                     int unitLoad = courses.getInt("unit_load");
                     
                     model.addRow(new Object[]{
                         false,
                         courseName.toUpperCase(),
                         courseCode.toUpperCase(),
                         unitLoad
                     });
                     
                 }
                 jTable1.setModel(model);
                 
                 TableColumnModel columnModel = jTable1.getColumnModel();
                 if (columnModel.getColumnCount() > 3) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.LEFT);
            columnModel.getColumn(3).setCellRenderer(centerRenderer);
        }
                 
                setupTableColumns();
                model.addTableModelListener(e ->{
            if (e.getColumn() == 0){
                updateTotalUnits();
            }
        });
   
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading courses: " + e.getMessage());
        }  
        
}
    private void updateTotalUnits(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int totalUnits = 0;
        
        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean isSelected = (Boolean) model.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                int units = (Integer) model.getValueAt(i, 3);
                totalUnits += units;
            }
        }
        totalUnitsLabel.setText(String.format("Total Units: %d/%d units", totalUnits, MAX_UNITS));
        if (totalUnits > MAX_UNITS) {
            totalUnitsLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this,
                "Warning: Total units selected (" + totalUnits + ") exceed the maximum allowed (" + MAX_UNITS + ")",
                "Unit Load Warning",
                JOptionPane.WARNING_MESSAGE);
        } else {
            totalUnitsLabel.setForeground(Color.BLACK);
        }
    
        
    }
    private void setupTableColumns(){
        TableColumn selectColumn = jTable1.getColumnModel().getColumn(0);
            selectColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
            selectColumn.setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected(value != null && (Boolean) value);
                    return checkBox;
                }
            });
            
            JScrollPane scrollPane = new JScrollPane(jTable1);
            scrollPane.setPreferredSize(new Dimension(500, 300));
            
            coursePanel.removeAll();
            coursePanel.setLayout(new BorderLayout());
            
            JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            labelPanel.add(totalUnitsLabel);
            
            coursePanel.add(scrollPane, BorderLayout.CENTER);
            coursePanel.add(labelPanel, BorderLayout.SOUTH);
            
            coursePanel.revalidate();
            coursePanel.repaint();
            
    }
    private void registerCourses(){
        try{
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            String studentName = txt_name.getText();
            String regNo = txt_regno.getText();
            String selectedLevel = combo_level.getSelectedItem().toString();
            String selectedSession = combo_session.getSelectedItem().toString();
            String selectedSemester = combo_semester.getSelectedItem().toString();

            
            if(studentName.isEmpty() || regNo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields");
                return;
            }
            int totalUnits = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                Boolean isSelected = (Boolean) model.getValueAt(i, 0);
                if (isSelected != null && isSelected) {
                    totalUnits += (Integer) model.getValueAt(i, 3);
                }
            }
            if (totalUnits > MAX_UNITS) {
                JOptionPane.showMessageDialog(this,
                    "Cannot register. Total units (" + totalUnits + ") exceed maximum allowed (" + MAX_UNITS + ")",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
           
            String registrationQuery = "INSERT INTO registrations (student_name, reg_no, course_code, course_name, "
                    + "level, session, semester , unit_load) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(registrationQuery);
            boolean coursesSelected = false;
            
            System.out.println("Total Columns: " + model.getColumnCount());
            
            for (int i = 0; i < model.getRowCount(); i++) {
                Boolean isSelected = (Boolean) model.getValueAt(i, 0);
                
                if (isSelected !=null && isSelected) {
                    coursesSelected = true;
                    String courseName = (String) model.getValueAt(i, 1);
                    String courseCode = (String) model.getValueAt(i, 2);
                    int unitLoad = (Integer) model.getValueAt(i, 3);
                    System.out.println("Course Code: " + courseCode);

                    pstmt.setString(1, studentName);
                    pstmt.setString(2, regNo);
                    pstmt.setString(3, courseCode);
                    pstmt.setString(4, courseName);
                    pstmt.setString(5, selectedLevel);
                    pstmt.setString(6, selectedSession);
                    pstmt.setString(7, selectedSemester);
                    pstmt.setInt(8, unitLoad);
                    
                    pstmt.executeUpdate();
                }
            }
            
            if (!coursesSelected) {
                JOptionPane.showMessageDialog(this, "Please select at least one course");
                return;
            }
            
            JOptionPane.showMessageDialog(this, "Courses registered successfully!");
            int printChoice = JOptionPane.showConfirmDialog(this,
            "Do you want to print your registration?",
            "Print Confirmation",
            JOptionPane.YES_NO_OPTION);
            
            if (printChoice == JOptionPane.YES_OPTION) {
            printPage();
            }
            
            clearForm();
            
            
        }catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error registering courses: " + e.getMessage());
        }
}
    private void clearForm() {
        txt_name.setText("");
        txt_regno.setText("");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(false, i, 0);
        }
        updateTotalUnits();
    }
    
    @Override
    public void dispose() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.dispose();
    }
    
    private void printPage(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Course Registration Page");
        
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
            if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            g2d.setFont(new Font("Arial", Font.BOLD, 12));

            double scaleX = pageFormat.getImageableWidth() / getWidth();
            double scaleY = pageFormat.getImageableHeight() / getHeight();
            double scale = Math.min(scaleX, scaleY); // Maintain aspect ratio

            g2d.scale(scale, scale);

            // Print the entire JFrame (including all its components)
            printAll(g2d);

            return PAGE_EXISTS;
             }
    });
        if (!job.printDialog()) {
        return;
        }
        
        try {
            job.print();
            
        } catch (PrinterException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error during printing: " + e.getMessage());
        }
       
       
}
         
                
    
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_regno = new javax.swing.JTextField();
        combo_semester = new javax.swing.JComboBox<>();
        combo_level = new javax.swing.JComboBox<>();
        combo_session = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        coursePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_back = new javax.swing.JButton();
        btn_Register = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTER COURSES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 330, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 160));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("SESSION:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 200, 80, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("NAME:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 60, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("REG NO:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 60, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("LEVEL :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 60, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("SEMESTER :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 80, 30));

        txt_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 212, 400, 30));

        txt_regno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_regno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 140, 30));

        combo_semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRST SEMESTER", "SECOND SEMESTER" }));
        combo_semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_semesterActionPerformed(evt);
            }
        });
        getContentPane().add(combo_semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 150, 30));

        combo_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100 LEVEL", "200 LEVEL", "300 LEVEL", "400 LEVEL" }));
        combo_level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_levelActionPerformed(evt);
            }
        });
        getContentPane().add(combo_level, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 212, 150, 30));

        combo_session.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024/2025", "2023/2024", "2022/2023", "2021/2022", "2020/2021" }));
        combo_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_sessionActionPerformed(evt);
            }
        });
        getContentPane().add(combo_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 200, 140, 30));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("COURSES");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 130, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 1590, 30));

        coursePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        coursePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 1270, 270));

        getContentPane().add(coursePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 1370, 310));

        btn_back.setBackground(new java.awt.Color(0, 102, 102));
        btn_back.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 760, 350, -1));

        btn_Register.setBackground(new java.awt.Color(0, 102, 102));
        btn_Register.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_Register.setForeground(new java.awt.Color(255, 255, 255));
        btn_Register.setText("REGISTER");
        btn_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 760, 350, -1));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        ConfimPage back = new ConfimPage();
        back.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterActionPerformed
        try{
            registerCourses();
            Registration.refreshCurrentInstance();  // Refresh the registration history
            
        }catch(Exception e){
            e.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "An error occurred while registering courses: " + e.getMessage(),
            "Registration Error",
            JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_RegisterActionPerformed

    private void combo_levelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_levelActionPerformed
        
    }//GEN-LAST:event_combo_levelActionPerformed

    private void combo_semesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_semesterActionPerformed

    }//GEN-LAST:event_combo_semesterActionPerformed

    private void combo_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sessionActionPerformed
       
    }//GEN-LAST:event_combo_sessionActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
            

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Register;
    private javax.swing.JButton btn_back;
    private javax.swing.JComboBox<String> combo_level;
    private javax.swing.JComboBox<String> combo_semester;
    private javax.swing.JComboBox<String> combo_session;
    private javax.swing.JPanel coursePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_regno;
    // End of variables declaration//GEN-END:variables
}

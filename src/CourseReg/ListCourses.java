package CourseReg;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class ListCourses extends javax.swing.JFrame {
    private ResultSet courses;
    private String selectedLevel;
    private String selectedSession;
    private String selectedSemester;
    private Connection conn;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USER = "root";
    private static final String PASS = "Emmadom1234@";
    
    public ListCourses(String level, String session, String semester) {
        this.selectedLevel = level;
        this.selectedSession = session;
        this.selectedSemester = semester;
        
        initComponents();
        connectToDatabase();
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
    
    
    private void setupTable() {
        try {
            if(courses == null){
                System.out.println("Courses ResultSet is null");
                return;
            }
        
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"Select", "Course Name", "Course Code"}, 0) {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnIndex == 0 ? Boolean.class : String.class;
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
                     
                     model.addRow(new Object[]{
                         false,
                         courseName,
                         courseCode
                     });
                     
                 }
                jTable1.setModel(model);
                setupTableColumns();
   
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading courses: " + e.getMessage());
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
            coursePanel.add(scrollPane, BorderLayout.CENTER);
            coursePanel.revalidate();
            coursePanel.repaint();
            
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        coursePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_back = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LIST OF  COURSES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 350, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 160));

        coursePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        coursePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 1270, 330));

        getContentPane().add(coursePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 1370, 430));

        btn_back.setBackground(new java.awt.Color(0, 102, 102));
        btn_back.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 720, 350, -1));

        btn_delete.setBackground(new java.awt.Color(0, 102, 102));
        btn_delete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("DELETE COURSES");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 660, 350, -1));

        btn_insert.setBackground(new java.awt.Color(0, 102, 102));
        btn_insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_insert.setForeground(new java.awt.Color(255, 255, 255));
        btn_insert.setText("INSERT COURSES");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });
        getContentPane().add(btn_insert, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 660, 350, -1));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        CoursesAvailable back = new CoursesAvailable();
        back.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        deleteCourses delete = new deleteCourses();
        delete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        insertCourses insert = new insertCourses();
        insert.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_insertActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_insert;
    private javax.swing.JPanel coursePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

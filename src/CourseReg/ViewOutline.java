
package CourseReg;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;



public class ViewOutline extends javax.swing.JFrame {
    private JTable courseTable;
    private JScrollPane scrollPane;
    private Map<String, String> courseOutlines = new HashMap<>(); 
    
    public ViewOutline() {
        initComponents();
    }

    public ViewOutline(java.util.List<Object[]> courseData) {
        initComponents();
        displayCourses(courseData);
    }
    
    private void displayCourses(java.util.List<Object[]> courseData) {
        for (Object[] row : courseData) {
        courseOutlines.put((String)row[0], (String)row[2]);  // Store course code -> outline
        }
        
            String[] columnNames = {"Course Code", "Course Name", "Course Outline"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0){
                @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 2 ? JButton.class : Object.class;
            }
         };
            
            for (Object[] row : courseData){
                Object[] newRow = new Object[3]; 
                newRow[0] = row[0]; // Course Code
                newRow[1] = row[1]; // Course Name
                newRow[2] = "View"; // View button for Course Outline
                model.addRow(newRow);
            }
            
            viewPanel.removeAll();
            
            courseTable = new JTable(model);
            courseTable.setRowHeight(40);
            
            courseTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            courseTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            courseTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            
            TableColumn outlineColumn = courseTable.getColumnModel().getColumn(2);
            outlineColumn.setCellRenderer(new ButtonRenderer());
            outlineColumn.setCellEditor(new ButtonEditor());
            
            scrollPane = new JScrollPane(courseTable);
            
            viewPanel.setLayout(new BorderLayout());
            viewPanel.add(scrollPane, BorderLayout.CENTER);
            
            viewPanel.revalidate();
            viewPanel.repaint();
        
    }
    
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(0, 102, 102));
            setForeground(Color.WHITE);
            setForeground(Color.WHITE);
            setFont(new Font("Tahoma", Font.BOLD, 12));
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("View");
            return this;
        }
    }
    
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        
        public ButtonEditor() {
            super(new JCheckBox());
            button = new JButton("view");
            button.setOpaque(true);
            button.setBackground(new Color(0, 102, 102));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Tahoma", Font.BOLD, 12));
            
            button.addActionListener(e -> {
                int row = courseTable.getSelectedRow();
                if (row != -1) {
                    String courseCode = (String) courseTable.getValueAt(row, 0);
                    String courseName = (String) courseTable.getValueAt(row, 1);
                    displayCourseOutline(courseCode, courseName);
            }
              fireEditingStopped();  
            });
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            return button;
        }
        
        @Override
        public Object getCellEditorValue() {
            return "View";
        }
        
    }
    
    private void displayCourseOutline(String courseCode, String courseName) {
        // Create a new frame to display the course outline
        JFrame outlineFrame = new JFrame("Course Outline - " + courseCode);
        outlineFrame.setSize(800, 600);
        outlineFrame.setLocationRelativeTo(this);
        
        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 102));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Course Outline");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Add course details
        contentPanel.add(createDetailLabel("Course Code: " + courseCode));
        contentPanel.add(createDetailLabel("Course Name: " + courseName));
        
        String outline = courseOutlines.get(courseCode);
        if (outline != null) {
        // Split the outline by newlines if it contains multiple lines
        String[] outlinePoints = outline.split("\n");
        for (String point : outlinePoints) {
            contentPanel.add(createDetailLabel(point.trim()));
             }
        } else {
        contentPanel.add(createDetailLabel("No outline available for this course."));
        }
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0, 102, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.addActionListener(e -> outlineFrame.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        outlineFrame.add(mainPanel);
        outlineFrame.setVisible(true);
    }
    private JLabel createDetailLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        return label;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        viewPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("VIEW COURSE OUTLINE");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 410, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 180));

        viewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        viewPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 1150, -1));

        getContentPane().add(viewPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 1250, 480));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 783, 290, 40));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AdminPage admin = new AdminPage();
        admin.setVisible(true);
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
            java.util.logging.Logger.getLogger(ViewOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewOutline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewOutline().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
}

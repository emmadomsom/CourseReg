
package CourseReg;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;



public class ViewOutline extends javax.swing.JFrame {
    private JTable courseTable;
    private JScrollPane scrollPane;
    
    public ViewOutline() {
        initComponents();
    }

    public ViewOutline(java.util.List<Object[]> courseData) {
        initComponents();
        displayCourses(courseData);
    }
    
    private void displayCourses(java.util.List<Object[]> courseData) {
            String[] columnNames = {"Course Code", "Course Name", "Course Description"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            
            for (Object[] row : courseData){
                model.addRow(row);
            }
            
            viewPanel.removeAll();
            
            courseTable = new JTable(model);
            courseTable.setRowHeight(30);
            courseTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            courseTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            courseTable.getColumnModel().getColumn(2).setPreferredWidth(500);
            
            scrollPane = new JScrollPane(courseTable);
            
            viewPanel.setLayout(new BorderLayout());
            viewPanel.add(scrollPane, BorderLayout.CENTER);
            
            viewPanel.revalidate();
            viewPanel.repaint();
        
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
        ConfirmViewOutline confirmView = new ConfirmViewOutline();
        confirmView.setVisible(true);
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

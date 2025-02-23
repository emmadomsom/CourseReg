
package CourseReg;

import javax.swing.*;


public class SetRegMode extends javax.swing.JFrame {
    
    public SetRegMode() {
        initComponents();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        combo_semester1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btn_back1 = new javax.swing.JButton();
        btn_load1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        combo_session1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SET REGISTRATION MODE");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 440, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 180));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combo_semester1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_semester1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRST SEMESTER", "SECOND SEMESTER" }));
        combo_semester1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_semester1ActionPerformed(evt);
            }
        });
        jPanel3.add(combo_semester1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 380, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SET SEMESTER");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 110, -1));

        btn_back1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_back1.setText("BACK");
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });
        jPanel3.add(btn_back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 110, -1));

        btn_load1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_load1.setText("SET MODE OF REGISTRATION");
        btn_load1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_load1ActionPerformed(evt);
            }
        });
        jPanel3.add(btn_load1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 240, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SET SESSION");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 90, -1));

        combo_session1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_session1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024/2025", "2023/2024", "2022/2023", "2021/2022", "2020/2021" }));
        combo_session1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_session1ActionPerformed(evt);
            }
        });
        jPanel3.add(combo_session1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 380, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 670, 470));

        setBounds(0, 0, 1601, 909);
    }// </editor-fold>//GEN-END:initComponents

    private void combo_semester1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_semester1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_semester1ActionPerformed

    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        AdminPage admin = new AdminPage();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_back1ActionPerformed

    private void btn_load1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_load1ActionPerformed
    String selectedSession = combo_session1.getSelectedItem().toString();
    String selectedSemester = combo_semester1.getSelectedItem().toString();
    
    // Set the registration mode
    RegistrationSettings.getInstance().setRegistrationMode(selectedSession, selectedSemester);
    
    // Show confirmation message
    JOptionPane.showMessageDialog(this,
        "Registration mode set successfully!\n" +
        "Session: " + selectedSession + "\n" +
        "Semester: " + selectedSemester,
        "Success",
        JOptionPane.INFORMATION_MESSAGE);
        
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
            java.util.logging.Logger.getLogger(SetRegMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetRegMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetRegMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetRegMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SetRegMode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back1;
    private javax.swing.JButton btn_load1;
    private javax.swing.JComboBox<String> combo_semester1;
    private javax.swing.JComboBox<String> combo_session1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}

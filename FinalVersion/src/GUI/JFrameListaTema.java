/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Controller.listagem_de_perguntas;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vasco
 */
public class JFrameListaTema extends javax.swing.JFrame {

    /**
     * Creates new form JFrameListaTema
     */
    public JFrameListaTema() throws SQLException {
        initComponents();
        setDefaultCloseOperation(JFrameAddPergunta.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException {

        jLabelListaTemas = new javax.swing.JLabel();
        jScrollPaneListaTemas = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        botaoApagarTema = new javax.swing.JButton();
        botaoAdicionarTema = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        botaoback = new javax.swing.JButton();

        DefaultTableModel dtm = new DefaultTableModel(0,0){
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        String header[] = new String[] {"id", "Temas"};
        dtm.setColumnIdentifiers(header);
        jTable1.setModel(dtm);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelListaTemas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelListaTemas.setText("Lista de Temas");



        jScrollPaneListaTemas.setViewportView(jTable1);
        jTable1.getTableHeader().setReorderingAllowed(false);



        botaoApagarTema.setText("Apagar");
        botaoApagarTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(jTable1.isRowSelected(jTable1.getSelectedRow())) {
                    String id = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                    botaoApagarTemaActionPerformed(evt, id);
                    dispose();
                }
            }
        });

        botaoAdicionarTema.setText("Adicionar");
        botaoAdicionarTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    botaoAdicionarTemaActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                dispose();
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Unidade Curricular a que o tema pertence:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                dtm.setRowCount(0);
                String subject = (String) jComboBox1.getSelectedItem();
                List<List<String>> topic = new ArrayList<List<String>>();

                try {
                    topic = listagem_de_perguntas.getTopicandId(subject);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                for(int i = 0; i < topic.size(); i++){
                    List<String> a = topic.get(i);
                    dtm.addRow(new Object[] {a.get(0), a.get(1)});
                }
            }
        });

        botaoback.setText("Back");
        botaoback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrameMenuListarPerguntas jf1 = null;
                try {
                    jf1 = new JFrameMenuListarPerguntas();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                jf1.show();
                dispose();
            }
        });



        List<String> values = listagem_de_perguntas.getSubjects();
        for(int i = 0; i< values.size(); i++) {
            jComboBox1.addItem(values.get(i));
        }


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabelListaTemas))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoAdicionarTema, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                    .addComponent(botaoback)
                                    .addGap(90, 90, 90)
                                .addComponent(botaoApagarTema, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPaneListaTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelListaTemas)
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jScrollPaneListaTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoApagarTema, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(botaoAdicionarTema, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(botaoback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                .addGap(26, 26, 26))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents




    private void botaoApagarTemaActionPerformed(ActionEvent evt, String id) {//GEN-FIRST:event_botaoApagarTemaActionPerformed
        // TODO add your handling code here:
        JFramePopupConfirmarTema jf1 = new JFramePopupConfirmarTema(id);
        jf1.show(); //display JFrameMenuQuizManual
    }//GEN-LAST:event_botaoApagarTemaActionPerformed

    private void botaoAdicionarTemaActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_botaoAdicionarTemaActionPerformed

    JFramePopupAdicionarTema jf1 = new JFramePopupAdicionarTema();
    jf1.show(); //display JFrameMenuListarPerguntas

    dispose(); //fechar o frame atual
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAdicionarTemaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameListaTema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameListaTema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameListaTema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameListaTema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFrameListaTema().setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarTema;
    private javax.swing.JButton botaoApagarTema;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelListaTemas;
    private javax.swing.JScrollPane jScrollPaneListaTemas;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton botaoback;
    // End of variables declaration//GEN-END:variables
}

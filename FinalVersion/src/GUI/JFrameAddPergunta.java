/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Controller.listagem_de_perguntas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vasco
 */
public class JFrameAddPergunta extends javax.swing.JFrame {

    /**
     * Creates new form JFrameAddPergunta
     */
    public JFrameAddPergunta() throws SQLException {
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

        jComboBoxTemaPergunta = new javax.swing.JComboBox<>();
        jComboBoxTipoPerguntaPergunta = new javax.swing.JComboBox<>();
        jComboBoxUCPergunta = new javax.swing.JComboBox<>();
        jLabelUCPergunta = new javax.swing.JLabel();
        jLabelTemaPergunta = new javax.swing.JLabel();
        jLabelTipoPerguntaPergunta = new javax.swing.JLabel();
        botaoSubmitPergunta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        jComboBoxTemaPergunta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Escolha uma opção"}));
        jComboBoxTemaPergunta.setSelectedIndex(0);
        jComboBoxTemaPergunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTemaPerguntaActionPerformed(evt);
            }
        });

        jComboBoxTipoPerguntaPergunta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Escolha uma opção", "Escolha Múltipla", "Desenvolvimento", "Curta", "Calculada", "Correspondência de Colunas" }));
        jComboBoxTipoPerguntaPergunta.setSelectedIndex(0);
        jComboBoxTipoPerguntaPergunta.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxTipoPerguntaPerguntaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBoxTipoPerguntaPergunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoPerguntaPerguntaActionPerformed(evt);
            }
        });

        jComboBoxUCPergunta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha uma opção" }));
        jComboBoxUCPergunta.setSelectedIndex(0);
        jComboBoxUCPergunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTemaPergunta.removeAllItems();
                String subject = (String) jComboBoxUCPergunta.getSelectedItem();
                List<String> topic = new ArrayList<String>();

                jComboBoxTemaPergunta.addItem("Escolha uma opção");


                try {
                    topic = listagem_de_perguntas.getTopic(subject);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                for(int i = 0; i<topic.size(); i++) {
                    jComboBoxTemaPergunta.addItem(topic.get(i));
                }
            }
        });

        List<String> values = listagem_de_perguntas.getSubjects();

        for(int i = 0; i<values.size(); i++) {
            jComboBoxUCPergunta.addItem(values.get(i));
        }


        jLabelUCPergunta.setText("Unidade Curricular");

        jLabelTemaPergunta.setText("Tema");

        jLabelTipoPerguntaPergunta.setText("Tipo de Pergunta");



        botaoSubmitPergunta.setText("Submit");
        botaoSubmitPergunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(!jComboBoxTemaPergunta.getSelectedItem().toString().equals("Escolha uma opção") && !jComboBoxTipoPerguntaPergunta.getSelectedItem().toString().equals("Escolha uma opção")){
                    botaoSubmitPerguntaActionPerformed(evt);

                }

            }
        });



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTemaPergunta)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxTemaPergunta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxTipoPerguntaPergunta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxUCPergunta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTipoPerguntaPergunta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                        .addComponent(botaoSubmitPergunta)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelUCPergunta))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelUCPergunta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxUCPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTemaPergunta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxTemaPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTipoPerguntaPergunta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoPerguntaPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSubmitPergunta))
                .addGap(31, 31, 31))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTemaPerguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTemaPerguntaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTemaPerguntaActionPerformed

    private void jComboBoxTipoPerguntaPerguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoPerguntaPerguntaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoPerguntaPerguntaActionPerformed

    private void jComboBoxTipoPerguntaPerguntaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxTipoPerguntaPerguntaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:    
    }//GEN-LAST:event_jComboBoxTipoPerguntaPerguntaPopupMenuWillBecomeInvisible

    private void botaoSubmitPerguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSubmitPerguntaActionPerformed
        // TODO add your handling code here:
        int combobox1 =jComboBoxTipoPerguntaPergunta.getSelectedIndex();
    
    if(combobox1 == 1){
        String subject = jComboBoxUCPergunta.getSelectedItem().toString();
        String topic = jComboBoxTemaPergunta.getSelectedItem().toString();
        RespostaEscolhaMultipla jf4 = new RespostaEscolhaMultipla(subject, topic);
        jf4.show();
        dispose();
        
    }else if(combobox1 == 2){
        String subject = jComboBoxUCPergunta.getSelectedItem().toString();
        String topic = jComboBoxTemaPergunta.getSelectedItem().toString();
        RespostaDesenvolvimento jf5 = new RespostaDesenvolvimento(subject, topic);
        jf5.show();
        dispose();
        
    }else if(combobox1 == 3){
        String subject = jComboBoxUCPergunta.getSelectedItem().toString();
        String topic = jComboBoxTemaPergunta.getSelectedItem().toString();
        RespostaCurta jf6 = new RespostaCurta(subject, topic);
        jf6.show();
        dispose();
        
    }else if(combobox1 == 4){
        String subject = jComboBoxUCPergunta.getSelectedItem().toString();
        String topic = jComboBoxTemaPergunta.getSelectedItem().toString();
        RespostaCalculada jf7 = new RespostaCalculada();
        jf7.show();
        dispose();
        
    }else if(combobox1 == 5){
        String subject = jComboBoxUCPergunta.getSelectedItem().toString();
        String topic = jComboBoxTemaPergunta.getSelectedItem().toString();
        RespostaCorrespondencia jf8 = new RespostaCorrespondencia(subject, topic);
        jf8.show();
        dispose();}
    }//GEN-LAST:event_botaoSubmitPerguntaActionPerformed

    

    
    
    
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
            java.util.logging.Logger.getLogger(JFrameAddPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameAddPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameAddPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameAddPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFrameAddPergunta().setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoSubmitPergunta;
    private javax.swing.JComboBox<String> jComboBoxTemaPergunta;
    private javax.swing.JComboBox<String> jComboBoxTipoPerguntaPergunta;
    private javax.swing.JComboBox<String> jComboBoxUCPergunta;
    private javax.swing.JLabel jLabelTemaPergunta;
    private javax.swing.JLabel jLabelTipoPerguntaPergunta;
    private javax.swing.JLabel jLabelUCPergunta;
    // End of variables declaration//GEN-END:variables
}

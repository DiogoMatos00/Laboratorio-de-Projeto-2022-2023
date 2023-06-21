/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ldp_project_gui;

/**
 *
 * @author vasco
 */
public class RespostaCalculada extends javax.swing.JFrame {

    /**
     * Creates new form RespostaCalculada
     */
    public RespostaCalculada() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNomeRespostaCalculada = new javax.swing.JTextField();
        jTextFieldPerguntaRespostaCalculada = new javax.swing.JTextField();
        jScrollPaneFormulaRespostaCalculada = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPaneRespostaRespostaCalculada = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPaneToleranciaRespostaCalculada = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jScrollPaneAlgarismosRespostaCalculada = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jLabelFormulaRespostaCalculada = new javax.swing.JLabel();
        jLabelToleranciaRespostaCalculada = new javax.swing.JLabel();
        jLabelAlgarismosRespostaCalculada = new javax.swing.JLabel();
        botaoSubmeterRespostaCalculada = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextFieldNomeRespostaCalculada.setText("Título");

        jTextFieldPerguntaRespostaCalculada.setText("Pergunta");

        jTextPane1.setName(""); // NOI18N
        jScrollPaneFormulaRespostaCalculada.setViewportView(jTextPane1);

        jTextPane2.setText("Resposta");
        jTextPane2.setName(""); // NOI18N
        jScrollPaneRespostaRespostaCalculada.setViewportView(jTextPane2);

        jTextPane3.setName(""); // NOI18N
        jScrollPaneToleranciaRespostaCalculada.setViewportView(jTextPane3);

        jTextPane4.setName(""); // NOI18N
        jScrollPaneAlgarismosRespostaCalculada.setViewportView(jTextPane4);

        jLabelFormulaRespostaCalculada.setText("Fórmula:");

        jLabelToleranciaRespostaCalculada.setText("Tolerância:");

        jLabelAlgarismosRespostaCalculada.setText("Algarismos Significativos:");

        botaoSubmeterRespostaCalculada.setText("Submeter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoSubmeterRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelAlgarismosRespostaCalculada)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPaneAlgarismosRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelToleranciaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPaneToleranciaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldPerguntaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldNomeRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabelFormulaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPaneFormulaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jScrollPaneRespostaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(431, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jTextFieldNomeRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldPerguntaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPaneFormulaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFormulaRespostaCalculada))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPaneToleranciaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelToleranciaRespostaCalculada))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneAlgarismosRespostaCalculada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAlgarismosRespostaCalculada, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(botaoSubmeterRespostaCalculada)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(198, Short.MAX_VALUE)
                    .addComponent(jScrollPaneRespostaRespostaCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(110, 110, 110)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(RespostaCalculada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RespostaCalculada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RespostaCalculada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RespostaCalculada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RespostaCalculada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoSubmeterRespostaCalculada;
    private javax.swing.JLabel jLabelAlgarismosRespostaCalculada;
    private javax.swing.JLabel jLabelFormulaRespostaCalculada;
    private javax.swing.JLabel jLabelToleranciaRespostaCalculada;
    private javax.swing.JScrollPane jScrollPaneAlgarismosRespostaCalculada;
    private javax.swing.JScrollPane jScrollPaneFormulaRespostaCalculada;
    private javax.swing.JScrollPane jScrollPaneRespostaRespostaCalculada;
    private javax.swing.JScrollPane jScrollPaneToleranciaRespostaCalculada;
    private javax.swing.JTextField jTextFieldNomeRespostaCalculada;
    private javax.swing.JTextField jTextFieldPerguntaRespostaCalculada;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    // End of variables declaration//GEN-END:variables
}
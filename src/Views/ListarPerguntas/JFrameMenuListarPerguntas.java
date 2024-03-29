/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views.ListarPerguntas;

import Controller.listagem_de_perguntas;

import Views.MainMenu.*;
import Views.AddQuestion.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vasco
 */
public class JFrameMenuListarPerguntas extends JFrame {

    /**
     * Creates new form JFrameMenuListarPerguntas
     */
    public JFrameMenuListarPerguntas() throws SQLException {
        initComponents();


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() throws SQLException {

        jPanelListarPerguntas1 = new JPanel();
        jComboBoxTemaLista = new JComboBox<>();
        jComboBoxCadeiraLista = new JComboBox<>();
        jComboBoxTipoPerguntaLista = new JComboBox<>();
        jLabelCadeiraLista = new JLabel();
        jLabelTemaLista = new JLabel();
        jLabelTipoPerguntaLista = new JLabel();
        tituloListaDePerguntas = new JLabel();
        botaoMenuPrincipal_ListarPerguntas = new JButton();
        jScrollPane1 = new JScrollPane();
        botaoRemoverTema = new JButton();
        botaoRemoverUc = new JButton();
        JLabelRemoverTema = new JLabel();
        JLabelRemoverUC = new JLabel();
        JLabelAddPergunta = new JLabel();
        botaoAddPergunta = new JButton();
        jTable1 = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0,0){
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };


        String header[] = new String[] {"ID", "Descrição", "UC"};

        dtm.setColumnIdentifiers(header);
        jTable1.setModel(dtm);


        List<List<String>> questions = listagem_de_perguntas.getAllQuestions();
        for(int i = 0; i < questions.size(); i++){
            List<String> a = questions.get(i);
            dtm.addRow(new Object[] {a.get(0), a.get(1), a.get(2)});
        }

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    String id = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                    JFramePopupPergunta jfpopup = null;
                    try {
                        jfpopup = new JFramePopupPergunta(id);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    jfpopup.show();
                    dispose();
                }
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanelListarPerguntas1.setBorder(new LineBorder(new Color(153, 153, 153), 1, true));

        jComboBoxTemaLista.setModel(new DefaultComboBoxModel<>());
        jComboBoxTemaLista.setSelectedIndex(-1);
        jComboBoxTemaLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<List<String>> result = null;
                String topic = (String) jComboBoxTemaLista.getSelectedItem();
                String subject = (String) jComboBoxCadeiraLista.getSelectedItem();

                try {
                    result = listagem_de_perguntas.getCustomQuestion(String.format("SELECT QUESTION.QuestionID, QUESTION.Description, question_subject.SubjectName FROM QUESTION INNER JOIN topic ON question.TopicId = topic.TopicId INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId WHERE topic.TopicName = '%s';", topic));
                } catch (SQLException eSQL) {
                    throw new RuntimeException(eSQL);
                }

                if(topic == "--Default--"){
                    dtm.setRowCount(0);
                    List<List<String>> resultS = null;
                    try {
                        resultS = listagem_de_perguntas.getCustomQuestion(String.format("SELECT QUESTION.QuestionID, QUESTION.Description, question_subject.SubjectName FROM QUESTION INNER JOIN topic ON question.TopicId = topic.TopicId INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId WHERE question_subject.SubjectName = '%s';", subject));
                    } catch (SQLException eSQL) {
                        throw new RuntimeException(eSQL);
                    }

                    for (int i = 0; i < resultS.size(); i++) {
                        List<String> a = resultS.get(i);
                        dtm.addRow(new Object[]{a.get(0), a.get(1), a.get(2)});
                    }
                }

                if(topic != "--Default--") {
                    dtm.setRowCount(0);
                }

                for(int i = 0; i < result.size(); i++){
                    List<String> a = result.get(i);
                    dtm.addRow(new Object[] {a.get(0), a.get(1), a.get(2)});
                }
            }
        });


        jComboBoxCadeiraLista.setModel(new DefaultComboBoxModel<>());
        jComboBoxCadeiraLista.setSelectedIndex(-1);
        jComboBoxCadeiraLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBoxTemaLista.removeAllItems();
                String subject = (String) jComboBoxCadeiraLista.getSelectedItem();
                List<String> topic = new ArrayList<String>();


                try {
                    topic = listagem_de_perguntas.getTopic(subject);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                topic.add(0, "--Default--");

                for(int i = 0; i<topic.size(); i++) {
                    jComboBoxTemaLista.addItem(topic.get(i));
                }

                List<List<String>> result = null;
                try {
                    result = listagem_de_perguntas.getCustomQuestion(String.format("SELECT QUESTION.QuestionID, QUESTION.Description, question_subject.SubjectName FROM QUESTION INNER JOIN topic ON question.TopicId = topic.TopicId INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId WHERE question_subject.SubjectName = '%s';", subject));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if(subject == "--Default--"){
                    dtm.setRowCount(0);
                    List<List<String>> questions = null;
                    try {
                        questions = listagem_de_perguntas.getAllQuestions();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    for(int i = 0; i < questions.size(); i++){
                        List<String> a = questions.get(i);
                        dtm.addRow(new Object[] {a.get(0), a.get(1), a.get(2)});
                    }
                }

                if(subject != "--Default--") {
                    dtm.setRowCount(0);
                }
                for(int i = 0; i < result.size(); i++){
                    List<String> a = result.get(i);
                    dtm.addRow(new Object[] {a.get(0), a.get(1), a.get(2)});
                }
            }
        });

        List<String> values = listagem_de_perguntas.getSubjects();
        values.add(0, "--Default--");

        for(int i = 0; i<values.size(); i++) {
            jComboBoxCadeiraLista.addItem(values.get(i));
        }


        jComboBoxTipoPerguntaLista.setModel(new DefaultComboBoxModel<>(new String[] {"--Default--", "Escolha Múltipla", "Desenvolvimento", "Curta", "Calculada", "Correspondência de Colunas" }));
        jComboBoxTipoPerguntaLista.setSelectedIndex(0);
        jComboBoxTipoPerguntaLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBoxTipoPerguntaListaActionPerformed(evt);
                List<List<String>> result = null;
                String topic = (String) jComboBoxTemaLista.getSelectedItem();
                String subject = (String) jComboBoxCadeiraLista.getSelectedItem();
                String qType = (String) jComboBoxTipoPerguntaLista.getSelectedItem();

                try {
                    result = listagem_de_perguntas.getCustomQuestion(String.format("SELECT QUESTION.QuestionID, QUESTION.Description, question_subject.SubjectName FROM QUESTION INNER JOIN topic ON question.TopicId = topic.TopicId INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId WHERE QUESTION.Type = '%s';", qType));
                } catch (SQLException eSQL) {
                    throw new RuntimeException(eSQL);
                }

                if(qType == "--Default--"){
                    if(topic == "--Default--"){
                        dtm.setRowCount(0);
                        result = null;
                        try {
                            result = listagem_de_perguntas.getCustomQuestion(String.format("SELECT QUESTION.QuestionID, QUESTION.Description, question_subject.SubjectName FROM QUESTION INNER JOIN topic ON question.TopicId = topic.TopicId INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId WHERE question_subject.SubjectName = '%s';", subject));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        for (int i = 0; i < result.size(); i++) {
                            List<String> a = result.get(i);
                            dtm.addRow(new Object[]{a.get(0), a.get(1), a.get(2)});
                        }
                    }

                    if(topic != "--Default--") {
                        dtm.setRowCount(0);
                        try {
                            result = listagem_de_perguntas.getCustomQuestion(String.format("SELECT QUESTION.QuestionID, QUESTION.Description, question_subject.SubjectName FROM QUESTION INNER JOIN topic ON question.TopicId = topic.TopicId INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId WHERE topic.TopicName = '%s';", topic));
                        } catch (SQLException eSQL) {
                            throw new RuntimeException(eSQL);
                        }
                        for (int i = 0; i < result.size(); i++) {
                            List<String> a = result.get(i);
                            dtm.addRow(new Object[]{a.get(0), a.get(1), a.get(2)});
                        }
                    }

                    if (subject == "--Default--") {
                        dtm.setRowCount(0);
                        List<List<String>> questions = null;
                        try {
                            questions = listagem_de_perguntas.getAllQuestions();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        for (int i = 0; i < questions.size(); i++) {
                            List<String> a = questions.get(i);
                            dtm.addRow(new Object[]{a.get(0), a.get(1), a.get(2)});
                        }
                    }
                }else{
                    dtm.setRowCount(0);
                    if(topic == "--Default--"){
                        dtm.setRowCount(0);
                        result = null;
                        try {
                            result = listagem_de_perguntas.getCustomQuestion(String.format("SELECT QUESTION.QuestionID, QUESTION.Description, question_subject.SubjectName FROM QUESTION INNER JOIN topic ON question.TopicId = topic.TopicId INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId WHERE question_subject.SubjectName = '%s' AND question.Type = '%s';", subject, qType));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        for (int i = 0; i < result.size(); i++) {
                            List<String> a = result.get(i);
                            dtm.addRow(new Object[]{a.get(0), a.get(1), a.get(2)});
                        }
                    }

                    if(topic != "--Default--") {
                        dtm.setRowCount(0);
                        try {
                            result = listagem_de_perguntas.getCustomQuestion(String.format("SELECT QUESTION.QuestionID, QUESTION.Description, question_subject.SubjectName FROM QUESTION INNER JOIN topic ON question.TopicId = topic.TopicId INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId WHERE topic.TopicName = '%s' AND question.Type = '%s';", topic, qType));
                        } catch (SQLException eSQL) {
                            throw new RuntimeException(eSQL);
                        }
                        for (int i = 0; i < result.size(); i++) {
                            List<String> a = result.get(i);
                            dtm.addRow(new Object[]{a.get(0), a.get(1), a.get(2)});
                        }
                    }

                    if (subject == "--Default--") {
                        dtm.setRowCount(0);
                        List<List<String>> questions = null;
                        try {
                            questions = listagem_de_perguntas.getCustomQuestion(String.format("SELECT QUESTION.QuestionID, QUESTION.Description, question_subject.SubjectName FROM QUESTION INNER JOIN topic ON question.TopicId = topic.TopicId INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId WHERE question.Type = '%s';",  qType));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        for (int i = 0; i < questions.size(); i++) {
                            List<String> a = questions.get(i);
                            dtm.addRow(new Object[]{a.get(0), a.get(1), a.get(2)});
                        }
                    }

                }
            }
        });

        jLabelCadeiraLista.setText("Cadeira");

        jLabelTemaLista.setText("Tema");

        jLabelTipoPerguntaLista.setText("Tipo de pergunta");


        GroupLayout jPanelListarPerguntas1Layout = new GroupLayout(jPanelListarPerguntas1);
        jPanelListarPerguntas1.setLayout(jPanelListarPerguntas1Layout);
        jPanelListarPerguntas1Layout.setHorizontalGroup(
                jPanelListarPerguntas1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelListarPerguntas1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelListarPerguntas1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelTipoPerguntaLista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelTemaLista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelCadeiraLista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxTipoPerguntaLista, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxTemaLista, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxCadeiraLista, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        )));
        jPanelListarPerguntas1Layout.setVerticalGroup(
                jPanelListarPerguntas1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanelListarPerguntas1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabelCadeiraLista)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxCadeiraLista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelTemaLista)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTemaLista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelTipoPerguntaLista)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTipoPerguntaLista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
        ));

        tituloListaDePerguntas.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
        tituloListaDePerguntas.setText("Lista de Perguntas");

        botaoMenuPrincipal_ListarPerguntas.setText("Menu Principal");
        botaoMenuPrincipal_ListarPerguntas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botaoMenuPrincipal_ListarPerguntasActionPerformed(evt);
            }
        });

        botaoRemoverTema.setText("*");
        botaoRemoverTema.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    botaoRemoverTemaActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        botaoRemoverUc.setText("*");
        botaoRemoverUc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    botaoRemoverUcActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        JLabelRemoverTema.setText("Temas");

        JLabelRemoverUC.setText("Unidades Curriculares");

        JLabelAddPergunta.setText("Pergunta");

        botaoAddPergunta.setText("+");
        botaoAddPergunta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    botaoAddPerguntaActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jScrollPane1.setViewportView(jTable1);


        TableColumn column1 = jTable1.getColumnModel().getColumn(0);
        column1.setPreferredWidth(5);

        TableColumn column2 = jTable1.getColumnModel().getColumn(1);
        column2.setPreferredWidth(350);

        TableColumn column3 = jTable1.getColumnModel().getColumn(2);
        column3.setPreferredWidth(30);

        jTable1.getTableHeader().setReorderingAllowed(false);

        column1.setResizable(false);
        column2.setResizable(false);
        column3.setResizable(false);


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(botaoMenuPrincipal_ListarPerguntas)
                                .addGap(277, 277, 277)
                                .addComponent(tituloListaDePerguntas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(462, 462, 462))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(botaoAddPergunta)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(JLabelAddPergunta, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(botaoRemoverTema)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JLabelRemoverTema)
                                                .addGap(37, 37, 37)
                                                .addComponent(botaoRemoverUc)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JLabelRemoverUC)
                                                .addGap(747, 747, 747))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanelListarPerguntas1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 815, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(tituloListaDePerguntas, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                        .addComponent(botaoMenuPrincipal_ListarPerguntas, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanelListarPerguntas1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(botaoRemoverUc)
                                                .addComponent(JLabelRemoverUC))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(botaoRemoverTema)
                                                .addComponent(JLabelRemoverTema))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(botaoAddPergunta)
                                                .addComponent(JLabelAddPergunta)))
                                .addGap(27, 27, 27))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void botaoMenuPrincipal_ListarPerguntasActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:

        JFrameMenuPrincipal jf1 = new JFrameMenuPrincipal();
        jf1.show(); //display JFrameMenuQuizManual

        dispose(); //fechar o frame atual
    }

    private void botaoAddPerguntaActionPerformed(ActionEvent evt) throws SQLException {
        JFrameAddPergunta jf4 = new JFrameAddPergunta();
        jf4.show(); //display JFrameMenuQuizManual        // TODO add your handling code here:
    }

    private void botaoRemoverTemaActionPerformed(ActionEvent evt) throws SQLException {
        JFrameListaTema jf5 = new JFrameListaTema();
        jf5.show(); //display JFrameMenuQuizManual
        dispose();
    }

    private void botaoRemoverUcActionPerformed(ActionEvent evt) throws SQLException {
        // TODO add your handling code here:
        JFrameListaUC jf6 = new JFrameListaUC();
        jf6.show(); //display JFrameMenuQuizManual
        dispose();
    }

    private void jComboBoxTipoPerguntaListaActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jComboBoxCadeiraListaActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

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
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameMenuListarPerguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMenuListarPerguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMenuListarPerguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMenuListarPerguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFrameMenuListarPerguntas().setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify
    private JLabel JLabelAddPergunta;
    private JLabel JLabelRemoverTema;
    private JLabel JLabelRemoverUC;
    private JButton botaoAddPergunta;
    private JButton botaoMenuPrincipal_ListarPerguntas;
    private JButton botaoRemoverTema;
    private JButton botaoRemoverUc;
    private JComboBox<String> jComboBoxCadeiraLista;
    private JComboBox<String> jComboBoxTemaLista;
    private JComboBox<String> jComboBoxTipoPerguntaLista;
    private JLabel jLabelCadeiraLista;
    private JLabel jLabelTemaLista;
    private JLabel jLabelTipoPerguntaLista;
    private JPanel jPanelListarPerguntas1;
    private JScrollPane jScrollPane1;
    private JLabel tituloListaDePerguntas;
    private JTable jTable1;
    // End of variables declaration


}
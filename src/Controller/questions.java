// CRIAR UMA CONEXÂO CADA VEZ Q QUEREMOS ALGO DA BASE DE DADOS <- Sim
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

public class questions {

    public static void createQuestion(String Description, String Difficulty, String Type, List<String> answer, String Subject, String theme) throws SQLException {
        // Por agora lista mas poderá ser modificado
        // Descrição do q acontece aqui
        // TODOS OS PARAMETROS TEM DE PASSAR NUM CONTROLO ANTES DE VIREM PARA ESTE METHOD.
        
        String question_query = String.format("INSERT INTO QUESTION (DESCRIPTION, DIFFICULTY, TYPE) VALUES (%s, %s, %s);", Description, Difficulty, Type);
        
        // ########################
        // Conexão à base de dados
        Connection conn = connection.connect();
        connection.execute_query(conn, question_query);

        String id = connection.execute_query(conn, "SELECT LAST_INSERT_ID();").getString(1); // TESTAR ISTO PARA VER SE FUNCIONA

        String topic_query = String.format("INSERT INTO TOPIC VALUES (%s, %s);", theme, id);
        String subject_query = String.format("INSERT INTO QUESTION_SUBJECT VALUES (%s, %s);", Subject, id);

        connection.execute_query(conn, topic_query);
        connection.execute_query(conn, subject_query);
        
        switch(Type){
            case "Escolha Múltipla":
                // VER COMO o parametro answer estara configurado para tratar os dados.

                connection.execute_query(conn, String.format("INSERT INTO MULTIPLECHOICE VALUES ('Periodo da Realeza', FALSE, FALSE, '2');"));
                // INSERT INTO MULTIPLECHOICE VALUES ('Periodo da Realeza', FALSE, FALSE, '2');
                // INSERT INTO MULTIPLECHOICE VALUES ('Periodo da Democracia', FALSE, TRUE, '2');
                // INSERT INTO MULTIPLECHOICE VALUES ('Periodo do Principado', FALSE, FALSE, '2');
                // INSERT INTO MULTIPLECHOICE VALUES ('Periodo do Baixo Império', FALSE, FALSE, '2');

            case "Resposta Múltipla":
                // VER COMO o parametro answer estara configurado para tratar os dados.

                // INSERT INTO MULTIPLECHOICE VALUES ('Modela o fluxo de tempo dentro de um sistema', TRUE, FALSE, '6');
                // INSERT INTO MULTIPLECHOICE VALUES ('Inclui atores e casos de uso', TRUE, TRUE, '6');
                // INSERT INTO MULTIPLECHOICE VALUES ('Especifica a decomposição funcional de um sistema', TRUE, FALSE, '6');
                // INSERT INTO MULTIPLECHOICE VALUES ('Especifica quem realiza quais tarefas no sistema que está a ser desenvolvido', TRUE, TRUE, '6');

            case "Desenvolvimento":
                // VER COMO o parametro answer estara configurado para tratar os dados.

                //INSERT INTO DEVELOPMENT VALUES ('As empresas cotadas possuem maiores exigências sobre a normalização contabilísticas, assim é obrigada a ter todos os tipos de demonstrações financeiras. Quanto menor a empresa, menor será a sua exigência sobre normalização contabilística, ou seja, também serão necessárias menos demonstrações financeiras.', '3' );

            case "Resposta Curta":
                // VER COMO o parametro answer estara configurado para tratar os dados.

                // INSERT INTO SHORT_ANSWER VALUES ("Opera com pouca ou nenhuma assistência, oferece suporte a decisões e problemas", "1");
            
            case "Correspondência de Colunas":
                // VER COMO o parametro answer estara configurado para tratar os dados.

                // INSERT INTO MATCHFOLLOWING VALUES ('Verificação e recuperação de erros e proteção contra falhas do sistema', 'Requisitos funcionais', '5');
                // INSERT INTO MATCHFOLLOWING VALUES ('Estados e condições que não devem acontecer', 'Requisitos de exclusão', '5');
                // INSERT INTO MATCHFOLLOWING VALUES ('Fiabilidade e disponibilidade desejadas para o sistema', 'Requisitos não funcionais', '5');

            case "Resposta Calculada":
                // VER COMO o parametro answer estara configurado para tratar os dados.
                //Duvida

                //INSERT INTO CALCULATEDQ VALUES ('80640', '4'); 

        }

        connection.disconnect(conn);

        //Exemplo de tratamento de dados:
        // while(result.next()){
        //     String data = "";
        //     for(int i = 1; i <= 1; i++){ //It will loop x times where x is the number of columns that we get.
        //         data += result.getString(i) + ";";
        //     }
        // }
    }

    static void editQuestion(String id, Hashtable<String, String> changes) {
        // Descrição do q acontece aqui

        // ########################
        // Conexão à base de dados

        // Pegar na hashtable e ver o q foi alterado e mudar para a base de dados. (ex:
        // checkar a key e mudar no respeito para a base de dados se não houver key não
        // há alterações).
    }

    static void deleteQuestion(String id) {
        // Descrição do q acontece aqui

        // ########################
        // Conexão à base de dados

        // Pegar numa questão pelo id e dar delete
    }

    static void getAll(String Subject) {
        // conseguir informação basica de todas as questões De uma cadeira ( Conseguir X
        // Querries q quando chegamos a ultima dá-mos load a mais x.) <- VER ISTO

        // ########################
        // Conexão à base de dados

    }

    static void getQuestion(String id) {
        // Conseguir informação detalhada de uma questão.

        // ########################
        // Conexão à base de dados
    }

    static void getCustom(Hashtable<String, String> Filter) {
        // Exprimental <- Conseguir todas as perguntas q respondam aos critérios de um
        // filtro. Ver como fazer isto.
        Connection conn = connection.connect();
        ResultSet result = connection.execute_query(conn, "Select * from question");
    }
}
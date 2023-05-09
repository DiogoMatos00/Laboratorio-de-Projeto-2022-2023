// CRIAR UMA CONEXÂO CADA VEZ Q QUEREMOS ALGO DA BASE DE DADOS <- Sim
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Hashtable;
import java.util.List;

public class questions {

    public static void createQuestion(String Description, String Difficulty, String Type, Hashtable<String, List> answer, String Subject, String theme) throws SQLException {
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
            case "Escolha Múltipla" :
            case "Resposta Múltipla":
                Integer n_answerM = Integer.parseInt((String) answer.get("n_answer").get(0));

                for(int i = 0; i < n_answerM; i++){
                    List<String> row = answer.get(i);
                    String choice = row.get(0); // question's answer
                    Boolean val = Boolean.parseBoolean(row.get(1)); // Bool to check if it is "Escolha múltipla" or "Resposta Múltipla "
                    Boolean torf = Boolean.parseBoolean(row.get(2)); // Bool to check if a answer is true or false.   

                    connection.execute_query(conn, String.format("INSERT INTO MULTIPLECHOICE VALUES((%s, %b, %b, %s);", choice, val, torf, id));
                }
                break;

            case "Desenvolvimento":
                String row_d = (String) answer.get("answer").get(0);
                connection.execute_query(conn, String.format("INSERT INTO DEVELOPMENT VALUES (%s, %s);", row_d, id));

                break;
        
            case "Resposta Curta":
                String row_c = (String) answer.get("answer").get(0);
                connection.execute_query(conn, String.format("INSERT INTO SHORT_ANSWER VALUES (%s, %s);", row_c, id));

                break;
    

            case "Correspondência de Colunas":
                Integer n_answerC = Integer.parseInt((String) answer.get("n_answer").get(0));

                for(int i = 0; i < n_answerC; i++){
                    List<String> row = answer.get(i);
                    String left = row.get(0);
                    String right = row.get(1);
                    connection.execute_query(conn, String.format("INSERT INTO MATCHFOLLOWING VALUES (%s, %s, %s);",left, right, id));
                }

                break;

            case "Resposta Calculada":
                Integer n_values = Integer.parseInt((String) answer.get("n_values").get(0));
                for (int i = 0; i < n_values; i++){
                    List<String> row = answer.get(i);
                    String valA = row.get(0);
                    String valB = row.get(1);
                    String buffer = row.get(2);

                    connection.execute_query(conn, String.format("INSERT INTO VALUES_C VALUES(%s, %s, %s, %s);", valA, valB, buffer, id));
                }
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

    static void editQuestion(String id, Hashtable<String, String> changes) throws SQLException {
        // Descrição do q acontece aqui

        // ########################
        // Conexão à base de dados
        Connection conn = connection.connect();
        
        String query = String.format("SELECT Type FROM Question WHERE id=%s;", id);

        String topic = connection.execute_query(conn, query).getString(1);

        switch(topic){

        }



        // SELECT DISTINCT TABLE_NAME 
        // FROM INFORMATION_SCHEMA.COLUMNS
        // WHERE COLUMN_NAME IN ('Product')
        //     AND TABLE_SCHEMA='YourDatabase';

        // Pegar na hashtable e ver o q foi alterado e mudar para a base de dados. (ex:
        // checkar a key e mudar no respeito para a base de dados se não houver key não
        // há alterações).
    }

    static void deleteQuestion(String id) {
        // Descrição do q acontece aqui

        // ########################
        // Conexão à base de dados
        Connection conn = connection.connect();
        connection.execute_query(conn, String.format("DELETE FROM Question WHERE id = %s;", id));
        connection.disconnect(conn);

        // Pegar numa questão pelo id e dar delete
    }

    static ResultSet getAll(String Subject) {
        // conseguir informação basica de todas as questões De uma cadeira ( Conseguir X
        // Querries q quando chegamos a ultima dá-mos load a mais x.) <- VER ISTO

        // ########################
        // Conexão à base de dados
        Connection conn = connection.connect();
        ResultSet result = connection.execute_query(conn, String.format("SELECT * FROM Question;"));
        connection.disconnect(conn);

        return result;
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
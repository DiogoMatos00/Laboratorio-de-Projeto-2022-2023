// CRIAR UMA CONEXÂO CADA VEZ Q QUEREMOS ALGO DA BASE DE DADOS <- Sim
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Hashtable;
import java.util.List;


public class questions {

    public static void createQuestion() { //String Description, String Difficulty, String Type, List<String> answer, String Subject, String theme
        // Por agora lista mas poderá ser modificado
        // Descrição do q acontece aqui

        // ########################
        // Conexão à base de dados
        Connection conn = connection.connect();
        ResultSet result = connection.execute_query(conn, "Select * from question");

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
    }
}
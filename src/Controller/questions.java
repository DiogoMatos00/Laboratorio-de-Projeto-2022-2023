// main
// CRIAR UMA CONEXÂO CADA VEZ Q QUEREMOS ALGO DA BASE DE DADOS?


package Controller;

import java.util.Hashtable;
import java.util.List;

public class questions {

    static void createQuestion(String Description, String Difficulty, String Type, List<String> answer, String Subject, String theme) { //Por agora lista mas poderá ser modificado
        //Descrição do q acontece aqui

        // ########################
        // Conexão à base de dados


        // Popular todas as tabelas com os dados acima.
    }

    static void editQuestion(String id, Hashtable<String,String> changes){
        //Descrição do q acontece aqui

        // ########################
        // Conexão à base de dados

        // Pegar na hashtable e ver o q foi alterado e mudar para a base de dados. (ex: checkar a key e mudar no respeito para a base de dados se não houver key não há alterações).
    }

    static void deleteQuestion(String id){
        //Descrição do q acontece aqui

        // ########################
        // Conexão à base de dados

        //Pegar numa questão pelo id e dar delete
    }

    static void getAll(){
        //conseguir informação basica de todas as questões

        // ########################
        // Conexão à base de dados

    }

    static void getQuestion(String id){
        //Conseguir informação detalhada de uma questão.

        // ########################
        // Conexão à base de dados
    }
}
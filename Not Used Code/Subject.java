package Controller;

import java.sql.Connection;
import java.sql.ResultSet;


// IMPORTANTE: SE DER DELETE A UM SUBJECT SE DA DELETE A TODOS OS TOPICOS LIGADOS E A TODAS AS PERGUNTS LIGADAS AOS TOPICOS


public class Subject {
    public static int createSubject(String Subject) {

        //** Returns: **\\
        // 0 -> Erro SQLException
        // 1 -> Erro Subject(UC) já existente
        // 2 -> Subject(UC) Criado com sucesso


        ResultSet resultSet;
        Connection conn = connection.connect();

        try {
            resultSet = connection.execute_query(
                    conn, String.format(
                            "SELECT SubjectName FROM Question_Subject WHERE SubjectName = %s;", Subject));

        } catch (Exception SQLException) {
            System.out.println(SQLException);
            connection.disconnect(conn);
            return 0;
        }

        try {
            if (resultSet.next()) {
                // Já existe este subject(UC) na base de dados.
                connection.disconnect(conn);
                return 1;
            } else {
                // Inserir o Subject(UC) à base de dados.
                connection.execute_query(
                        conn, String.format(
                                "INSERT INTO Question_Subject VALUES (1, %s);", Subject));

                connection.disconnect(conn);
                return 2;
            }
        } catch (Exception SQLException) {
            System.out.println(SQLException);
            connection.disconnect(conn);
            return 0;
        }
    }

    public static int deleteSubject(String Subject) {
        //** Returns: **\\
        // 0 -> Erro SQLException
        // 1 -> Erro Subject(UC) não existente
        // 2 -> Subject(UC) deleted com sucesso

        ResultSet resultSet;
        Connection conn = connection.connect();

        try {
            resultSet = connection.execute_query(
                    conn, String.format(
                            "SELECT SubjectName FROM Question_Subject WHERE SubjectName = %s;", Subject));

        } catch (Exception SQLException) {
            System.out.println(SQLException);
            connection.disconnect(conn);
            return 0;
        }

        try {
            if (!resultSet.next()) {
                // Não existe este subject(UC) na base de dados.
                connection.disconnect(conn);
                return 1;
            } else {
                // Delete o Subject(UC) à base de dados.
                connection.execute_query(
                        conn, String.format(
                                "DELETE FROM Question_Subject WHERE SubjectName = %s;", Subject));

                connection.disconnect(conn);
                return 2;
            }
        } catch (Exception SQLException) {
            System.out.println(SQLException);
            connection.disconnect(conn);
            return 0;
        }
    }
}
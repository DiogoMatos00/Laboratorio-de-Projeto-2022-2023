package Controller;

import java.sql.Connection;
import java.sql.ResultSet;

// IMPORTANTE: SE DER DELETE A UM SUBJECT SE DA DELETE A TODOS OS TOPICOS LIGADOS E A TODAS AS PERGUNTS LIGADAS AOS TOPICOS

public class Topics {
    public static int createTopic(String Topic, String Subject) {

        // ** Returns: **\\
        // 0 -> Erro SQLException
        // 1 -> Erro Topic já existente
        // 2 -> Topico Criado com sucesso

        ResultSet resultSet;
        String SubjectId = "";
        Connection conn = connection.connect();

        try {
            SubjectId = connection.execute_query(
                    conn, String.format(
                            "SELECT QuestionSubjectId WHERE SubjectName = '%s';", Subject))
                    .getString("SubjectName");

        } catch (Exception SQLException) {
            System.out.println(SQLException);
            connection.disconnect(conn);
            return 0;
        }

        try {
            resultSet = connection.execute_query(
                    conn, String.format(
                            "SELECT TopicName FROM Topic WHERE TopicName = '%s' and  QuestionSubjectId = '%s';",
                            Topic, Subject));

        } catch (Exception SQLException) {
            System.out.println(SQLException);
            connection.disconnect(conn);
            return 0;
        }

        try {
            if (resultSet.next()) {
                // Já existe este Topic na base de dados.
                connection.disconnect(conn);
                return 1;
            } else {
                // Inserir o Topic à base de dados.
                connection.execute_query(
                        conn, String.format(
                                "INSERT INTO Topic (TopicName, QuestionSubjectId) VALUES (%s, %s);", Topic, SubjectId));

                connection.disconnect(conn);
                return 2;
            }
        } catch (Exception SQLException) {
            System.out.println(SQLException);
            connection.disconnect(conn);
            return 0;
        }
    }

    public static int deleteTopic(String Topic, String Subject) {
        // ** Returns: **\\
        // 0 -> Erro SQLException
        // 1 -> Erro Topic não existente
        // 2 -> Topic deleted com sucesso

        ResultSet resultSet;
        String SubjectId = "";
        Connection conn = connection.connect();

        try {
            SubjectId = connection.execute_query(
                    conn, String.format(
                            "SELECT QuestionSubjectId WHERE SubjectName = '%s';", Subject))
                    .getString("SubjectName");

        } catch (Exception SQLException) {
            System.out.println(SQLException);
            connection.disconnect(conn);
            return 0;
        }



        try {
            resultSet = connection.execute_query(
                    conn, String.format(
                            "SELECT TopicName FROM Topic WHERE TopicName = '%s' and  QuestionSubjectId = '%s';",
                            Topic, Subject));

        } catch (Exception SQLException) {
            System.out.println(SQLException);
            connection.disconnect(conn);
            return 0;
        }

        try {
            if (!resultSet.next()) {
                // Não existe este Topic na base de dados.
                connection.disconnect(conn);
                return 1;
            } else {
                // Delete o Topic à base de dados.
                connection.execute_query(
                        conn, String.format(
                                "DELETE FROM Topic WHERE SubjectName = %s AND QuestionSubjectId = '%s';",
                                Subject, SubjectId));

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
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class listagem_de_perguntas {
    public static List<String> getSubjects() throws SQLException {
        Connection conn = connection.connect();
        ResultSet rs = connection.execute_query(conn, "SELECT SubjectName FROM question_subject;");

        List<String> values = new ArrayList<String>();

        while(rs.next()){
            values.add((String) rs.getObject(1));
        }

        rs.close();
        connection.disconnect(conn);
        return values;
    }

    public static List<String> getTopic(String Subject) throws SQLException {
        ResultSet rs = null;
        Connection conn = connection.connect();
        ResultSet subjectID = connection.execute_query(
                conn, String.format("SELECT QuestionSubjectId FROM Question_Subject WHERE SubjectName = '%s';", Subject));


        if(subjectID.next()){
            rs = connection.execute_query(
                conn, String.format("SELECT TopicName FROM Topic WHERE QuestionSUbjectId =  %d;", subjectID.getObject(1)));
        }

        List<String> values = new ArrayList<String>();

        if(rs !=null) {
            while (rs.next()) {
                values.add((String) rs.getObject(1));
            }

            rs.close();
        }
        connection.disconnect(conn);

        return values;
    }

    public static List<List<String>> getAllQuestions() throws SQLException {
        Connection conn = connection.connect();
        List<List<String>> values = new ArrayList<>();
        ResultSet rs = connection.execute_query(
                conn, "SELECT \n" +
                        "QUESTION.QuestionID,\n" +
                        "QUESTION.Description,\n" +
                        "question_subject.SubjectName\n" +
                        "FROM QUESTION \n" +
                        "INNER JOIN topic ON question.TopicId = topic.TopicId\n" +
                        "INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId ;");



        while(rs.next()){
            List<String> addQ = new ArrayList<String>();
            addQ.add(rs.getObject(1).toString());
            addQ.add((String) rs.getObject(2));
            addQ.add((String) rs.getObject(3));

            values.add(addQ);
        }

        rs.close();
        connection.disconnect(conn);
        return values;
    }

    public static List<List<String>> getCustomQuestion(String query) throws SQLException {
        Connection conn = connection.connect();
        List<List<String>> values = new ArrayList<>();
        ResultSet rs = connection.execute_query(
                conn, query);



        while(rs.next()){
            List<String> addQ = new ArrayList<String>();
            addQ.add(rs.getObject(1).toString());
            addQ.add((String) rs.getObject(2));
            addQ.add((String) rs.getObject(3));

            values.add(addQ);
        }

        rs.close();
        connection.disconnect(conn);
        return values;
    }

    public static List<String> getonequestion(String id) throws SQLException {
        Connection conn = connection.connect();
        List<String> values = new ArrayList<>();

        ResultSet rs = connection.execute_query(
                conn, String.format("SELECT \n" +
                        "QUESTION.QuestionID,\n" +
                        "QUESTION.Description,\n" +
                        "question_subject.SubjectName\n" +
                        "FROM QUESTION \n" +
                        "INNER JOIN topic ON question.TopicId = topic.TopicId\n" +
                        "INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId \n" +
                        "WHERE QUESTION.QuestionID = %s", id));

        if(rs.next()) {
            values.add(rs.getObject(1).toString());
            values.add((String) rs.getObject(2));
            values.add((String) rs.getObject(3));
        }

        rs.close();
        connection.disconnect(conn);

        return values;
    }

    public static void removequestion(String id){
        Connection conn = connection.connect();
        connection.update_query(
                conn, String.format("DELETE FROM QUESTION WHERE QUESTIONID = %s;", id));

        connection.disconnect(conn);
    }
}

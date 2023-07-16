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

    public static String getTopicID(String subject, String topic) throws SQLException {
        ResultSet rs = null;
        Connection conn = connection.connect();
        ResultSet subjectID = connection.execute_query(
                conn, String.format("SELECT QuestionSubjectId FROM Question_Subject WHERE SubjectName = '%s';", subject));


        String subjectid = null;
        if(subjectID.next()){
            subjectid = subjectID.getObject(1).toString();
        }




            rs = connection.execute_query(
                    conn, String.format("SELECT TopicId FROM Topic WHERE TopicName = '%s' AND QuestionSubjectId= '%s';", topic, subjectid));



        String value = null;
        if (rs != null) {
            if (rs.next()) {
                value = rs.getObject(1).toString();
            }

            rs.close();
        }

        connection.disconnect(conn);

        return value;
    }

    public static List<List<String>> getSubjectsandId() throws SQLException {
        Connection conn = connection.connect();
        ResultSet rs = connection.execute_query(conn, "SELECT SubjectName, QuestionSubjectId FROM question_subject;");

        List<List<String>> values = new ArrayList<List<String>>();

        while(rs.next()){
            List<String> add = new ArrayList<>();
            add.add(rs.getObject(2).toString());
            add.add((String) rs.getObject(1));
            values.add(add);
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

    public static String getTopicIdwithSubjectandtopic(String Subject, String topic) throws SQLException {
        ResultSet rs = null;
        Connection conn = connection.connect();
        ResultSet subjectID = connection.execute_query(
                conn, String.format("SELECT QuestionSubjectId FROM Question_Subject WHERE SubjectName = '%s';", Subject));


        if(subjectID.next()){
            rs = connection.execute_query(
                    conn, String.format("SELECT TopicId FROM Topic WHERE QuestionSUbjectId =  %d AND TopicName = '%s';", subjectID.getObject(1), topic));
        }

        String values = null;

        if(rs.next()) {
            values = ( rs.getObject(1).toString());

        }

        rs.close();
        connection.disconnect(conn);

        return values;
    }

    public static List<List<String>> getTopicandId(String Subject) throws SQLException {
        ResultSet rs = null;
        Connection conn = connection.connect();
        ResultSet subjectID = connection.execute_query(
                conn, String.format("SELECT QuestionSubjectId FROM Question_Subject WHERE SubjectName = '%s';", Subject));


        if(subjectID.next()){
            rs = connection.execute_query(
                    conn, String.format("SELECT TopicId, TopicName FROM Topic WHERE QuestionSUbjectId =  %d;", subjectID.getObject(1)));
        }

        List<List<String>> values = new ArrayList<List<String>>();

        if(rs !=null) {
            while (rs.next()) {
                List<String> add = new ArrayList<String>();
                add.add((String) rs.getObject(1).toString());
                add.add((String) rs.getObject(2));

                values.add(add);
            }

            rs.close();
        }
        connection.disconnect(conn);

        return values;
    }

    public static String isthereUCName(String name) throws SQLException {
        Connection conn = connection.connect();
        String values = null;
        ResultSet rs = connection.execute_query(
                conn, String.format("SELECT COUNT(1) FROM question_subject WHERE SubjectName = '%s';;", name
                ));

        if(rs.next()){
            values = rs.getObject(1).toString();
        }

        rs.close();
        connection.disconnect(conn);
        return values;
    }

    public static String isthereTopicName(String name, String subject) throws SQLException {
        Connection conn = connection.connect();
        String values = null;
        ResultSet rs = connection.execute_query(
                conn, String.format("SELECT COUNT(1) FROM topic INNER JOIN question_subject ON topic.QuestionSubjectId = question_subject.QuestionSubjectId WHERE topic.topicName = '%s' AND question_subject.SubjectName = '%s';", name, subject
                ));

        if(rs.next()){
            values = rs.getObject(1).toString();
        }

        rs.close();
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

    public static List<List<String>> getAllQuestionsWithType() throws SQLException {
        Connection conn = connection.connect();
        List<List<String>> values = new ArrayList<>();
        ResultSet rs = connection.execute_query(
                conn, "SELECT \n" +
                        "QUESTION.QuestionID,\n" +
                        "QUESTION.Description,\n" +
                        "question_subject.SubjectName,\n" +
                        "QUESTION.Type\n" +
                        "FROM QUESTION \n" +
                        "INNER JOIN topic ON question.TopicId = topic.TopicId\n" +
                        "INNER JOIN question_subject ON question_subject.QuestionSubjectId = topic.QuestionSubjectId ;");



        while(rs.next()){
            List<String> addQ = new ArrayList<String>();
            addQ.add(rs.getObject(1).toString());
            addQ.add((String) rs.getObject(2));
            addQ.add((String) rs.getObject(3));
            addQ.add((String) rs.getObject(4));

            values.add(addQ);
        }

        rs.close();
        connection.disconnect(conn);
        return values;
    }

    public static String getTopicIDFromQuestion(String query) throws SQLException {
        Connection conn = connection.connect();
        String result = null;
        assert conn != null;
        ResultSet rs = connection.execute_query(
                conn, query);


        if(rs.next()){
            result = rs.getObject(1).toString();
        }

        rs.close();
        connection.disconnect(conn);
        return result;
    }

    public static String maxid() throws SQLException {
        Connection conn = connection.connect();
        String result = null;
        ResultSet rs = connection.execute_query(
                conn, "SELECT max(QuestionId) FROM question;");


        if(rs.next()){
            result = rs.getObject(1).toString();
        }

        rs.close();
        connection.disconnect(conn);
        return result;
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

    public static List<List<String>> getCustomQuestionWithType(String query) throws SQLException {
        Connection conn = connection.connect();
        List<List<String>> values = new ArrayList<>();
        ResultSet rs = connection.execute_query(
                conn, query);



        while(rs.next()){
            List<String> addQ = new ArrayList<String>();
            addQ.add(rs.getObject(1).toString());
            addQ.add((String) rs.getObject(2));
            addQ.add((String) rs.getObject(3));
            addQ.add((String) rs.getObject(4).toString());

            values.add(addQ);
        }

        rs.close();
        connection.disconnect(conn);
        return values;
    }

    public static List<List<String>> getCustomQuestionWithSomeTypes(String query) throws SQLException {
        Connection conn = connection.connect();
        List<List<String>> values = new ArrayList<>();
        ResultSet rs = connection.execute_query(
                conn, query);



        while(rs.next()){
            List<String> addQ = new ArrayList<String>();
            addQ.add(rs.getObject(1).toString());

            values.add(addQ);
        }

        rs.close();
        connection.disconnect(conn);
        return values;
    }

    public static void addUC(String name) throws SQLException {
        Connection conn = connection.connect();
        connection.update_query(
                conn, String.format("INSERT INTO  Question_Subject (SubjectName) Values ('%s');", name
                ));

        connection.disconnect(conn);
    }

    public static void addQuestion(String query) throws SQLException {
        Connection conn = connection.connect();
        connection.update_query(
                conn, query
                );

        connection.disconnect(conn);
    }

    public static List<String> getquestionID(String query) throws SQLException {
        Connection conn = connection.connect();
        List<String> values = new ArrayList<>();
        ResultSet rs = connection.execute_query(
                conn, query);



        while(rs.next()){
            values.add(rs.getObject(1).toString());
        }

        rs.close();
        connection.disconnect(conn);
        return values;
    }

    public static void addTopic(String name, String subject) throws SQLException {
        Connection conn = connection.connect();
        String subjectid = null;
        ResultSet rs = connection.execute_query(
                conn, String.format("SELECT QuestionSubjectId FROM question_subject WHERE SubjectName = '%s';", subject
                ));

        if(rs.next()){
            subjectid = rs.getObject(1).toString();
        }

        connection.update_query(
                conn, String.format("INSERT INTO Topic (TopicName, QuestionSubjectId) Values ('%s', '%s');", name, subjectid
                ));

        connection.disconnect(conn);
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

    public static void removeSubject(String id){
        Connection conn = connection.connect();
        connection.update_query(
                conn, String.format("DELETE FROM question_Subject WHERE QuestionSubjectId = %s;", id));

        connection.disconnect(conn);
    }

    public static void removeTopic(String id){
        Connection conn = connection.connect();
        connection.update_query(
                conn, String.format("DELETE FROM Topic WHERE TopicId = %s;", id));

        connection.disconnect(conn);
    }
}

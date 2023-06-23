

import java.sql.Connection;
import java.sql.ResultSet;

public class listagem_de_perguntas {
    public static ResultSet getSubjects(){
        Connection conn = connection.connect();
        ResultSet result = connection.execute_query(conn, "SELECT SubjectName FROM Question_Subject;");

        return result;
    }
}

package dbToXML;
import java.sql.*;

public class Match {
    private int questionId;

    public Match(int questionId) {
        this.questionId = questionId;
    }

    public String generateMoodleXML() {
        StringBuilder xmlBuilder = new StringBuilder();

        try {
            // Connect to the database
            Connection connection = Controller.connection.connect();
            Statement statement = connection.createStatement();

            // Execute the query
            String query = "SELECT q.Description, mf.LeftSide, mf.RightSide " +
                    "FROM Question q " +
                    "JOIN MatchFollowing mf ON mf.QuestionId = q.QuestionId " +
                    "WHERE q.QuestionId = " + questionId;
            ResultSet resultSet = statement.executeQuery(query);

            // Check if the result set is empty
            if (!resultSet.next()) {
                // Close the database connection
                resultSet.close();
                statement.close();
                connection.close();

                return ""; // Return an empty string when the question doesn't exist
            }

            // Generate Moodle XML string
            xmlBuilder.append("<question type=\"matching\">\n");
            xmlBuilder.append("  <name>\n");
            xmlBuilder.append("    <text>").append("Question ").append(questionId).append("</text>\n");
            xmlBuilder.append("  </name>\n");

            String questionText = resultSet.getString("q.Description");
            xmlBuilder.append("  <questiontext format=\"html\">\n");
            xmlBuilder.append("    <text>").append(questionText).append("</text>\n");
            xmlBuilder.append("  </questiontext>\n");

            do {
                String leftSide = resultSet.getString("mf.LeftSide");
                String rightSide = resultSet.getString("mf.RightSide");

                xmlBuilder.append("  <subquestion format=\"html\">\n");
                xmlBuilder.append("    <text>").append(leftSide).append("</text>\n");
                xmlBuilder.append("    <answer>\n");
                xmlBuilder.append("      <text>").append(rightSide).append("</text>\n");
                xmlBuilder.append("    </answer>\n");
                xmlBuilder.append("  </subquestion>\n");
            } while (resultSet.next());

            xmlBuilder.append("</question>");

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return xmlBuilder.toString();
    }

    public static void main(String[] args) {
        int questionId = 1; // Replace with the desired question ID

        Match match = new Match(questionId);
        String xmlString = match.generateMoodleXML();

        System.out.println(xmlString);
    }
}

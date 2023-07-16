package dbToXML;
import java.sql.*;

public class ShortAnswer {
    private int questionId;

    public ShortAnswer(int questionId) {
        this.questionId = questionId;
    }

    public String generateMoodleXML() {
        StringBuilder xmlBuilder = new StringBuilder();

        try {
            // Connect to the database
            Connection connection = Controller.connection.connect();
            Statement statement = connection.createStatement();

            // Execute the query
            String query = "SELECT q.Description, sa.Answer_Placeholder " +
                    "FROM Question q " +
                    "JOIN ShortAnswer sa ON sa.QuestionId = q.QuestionId " +
                    "WHERE q.QuestionId = " + questionId + " AND q.Type = 'Resposta Curta'";
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
            String description = resultSet.getString("Description");
            String answerPlaceholder = resultSet.getString("Answer_Placeholder");

            xmlBuilder.append("<question type=\"shortanswer\">\n");
            xmlBuilder.append("  <name>\n");
            xmlBuilder.append("    <text>").append(questionId).append("</text>\n");
            xmlBuilder.append("  </name>\n");
            xmlBuilder.append("  <questiontext format=\"html\">\n");
            xmlBuilder.append("    <text>").append(description).append("</text>\n");
            xmlBuilder.append("  </questiontext>\n");
            xmlBuilder.append("  <answer fraction=\"100\">\n");
            xmlBuilder.append("    <text>").append(answerPlaceholder).append("</text>\n");
            xmlBuilder.append("    <feedback>\n");
            xmlBuilder.append("      <text>Correct!</text>\n");
            xmlBuilder.append("    </feedback>\n");
            xmlBuilder.append("  </answer>\n");
            xmlBuilder.append("</question>\n");

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
        int questionId = 138; // Replace with the desired question ID

        ShortAnswer sa = new ShortAnswer(questionId);
        String xmlString = sa.generateMoodleXML();

        System.out.println(xmlString);
    }
}

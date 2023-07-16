package dbToXML;
import java.sql.*;
import Controller.connection;

public class Essay {
    private int questionId;

    public Essay(int questionId) {
        this.questionId = questionId;
    }

    public String generateMoodleXML() {
        StringBuilder xmlBuilder = new StringBuilder();

        try {
            // Connect to the database
            Connection connection = Controller.connection.connect();
            Statement statement = connection.createStatement();

            // Execute the query
            String query = "SELECT q.Description " +
                    "FROM Question q " +
                    "JOIN Development d ON d.QuestionId = q.QuestionId " +
                    "WHERE q.QuestionId = " + questionId + " AND q.Type = 'Desenvolvimento'";
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

            xmlBuilder.append("<question type=\"essay\">\n");
            xmlBuilder.append("  <name>\n");
            xmlBuilder.append("    <text>Question ").append(questionId).append("</text>\n");
            xmlBuilder.append("  </name>\n");
            xmlBuilder.append("  <questiontext format=\"html\">\n");
            xmlBuilder.append("    <text>").append(description).append("</text>\n");
            xmlBuilder.append("  </questiontext>\n");
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
        int questionId = 137; // Replace with the desired question ID

        Essay essay = new Essay(questionId);
        String xmlString = essay.generateMoodleXML();

        System.out.println(xmlString);
    }
}

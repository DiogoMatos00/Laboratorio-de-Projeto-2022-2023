package dbToXML;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MultipleChoice {
    private int questionId;

    public MultipleChoice(int questionId) {
        this.questionId = questionId;
    }

    public String generateMoodleXML() {
        StringBuilder xmlBuilder = new StringBuilder();

        try {
            // Connect to the database
            Connection connection = Controller.connection.connect();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // Execute the query to get the correct answers
            String correctAnswersQuery = "SELECT Answer FROM MultipleChoice WHERE QuestionId = " + questionId + " AND Val = 1";
            ResultSet correctAnswersResultSet = statement.executeQuery(correctAnswersQuery);

            // Collect the correct answers into a list
            List<String> correctAnswersList = new ArrayList<>();
            while (correctAnswersResultSet.next()) {
                String correctAnswer = correctAnswersResultSet.getString("Answer");
                correctAnswersList.add(correctAnswer);
            }

            // Calculate the fraction per correct answer
            double fractionPerAnswer = 100.0 / correctAnswersList.size();

            // Execute the query to get all the answers
            String allAnswersQuery = "SELECT q.Description, mc.Answer, mc.Val " +
                    "FROM Question q " +
                    "JOIN MultipleChoice mc ON mc.QuestionId = q.QuestionId " +
                    "WHERE q.QuestionId = " + questionId;
            ResultSet allAnswersResultSet = statement.executeQuery(allAnswersQuery);

            // Check if the result set is empty
            if (!allAnswersResultSet.next()) {
                // Close the database connection
                allAnswersResultSet.close();
                correctAnswersResultSet.close();
                statement.close();
                connection.close();

                return ""; // Return an empty string when the question doesn't exist
            }

            // Generate Moodle XML string
            xmlBuilder.append("<question type=\"multichoice\">\n");
            xmlBuilder.append("  <name>\n");
            xmlBuilder.append("    <text>").append("Question ").append(questionId).append("</text>\n");
            xmlBuilder.append("  </name>\n");

            // Get the question text (assuming it's the same for all answers)
            String questionText = allAnswersResultSet.getString("q.Description");

            // Add the question text element
            xmlBuilder.append("  <questiontext format=\"html\">\n");
            xmlBuilder.append("    <text><![CDATA[").append(questionText).append("]]></text>\n");
            xmlBuilder.append("  </questiontext>\n");

            // Reset the result set pointer to the beginning
            allAnswersResultSet.beforeFirst();

            // Generate XML for each answer
            while (allAnswersResultSet.next()) {
                String answer = allAnswersResultSet.getString("mc.Answer");
                boolean val = allAnswersResultSet.getBoolean("mc.Val");

                double answerFraction = val ? fractionPerAnswer : 0.0;

                xmlBuilder.append("  <answer fraction=\"").append(answerFraction).append("\">\n");
                xmlBuilder.append("    <text><![CDATA[").append(answer).append("]]></text>\n");
                xmlBuilder.append("    <feedback>\n");
                xmlBuilder.append("      <text>").append(val ? "Correct!" : "Incorrect!").append("</text>\n");
                xmlBuilder.append("    </feedback>\n");
                xmlBuilder.append("  </answer>\n");
            }

            xmlBuilder.append("</question>");

            // Close the database connection
            allAnswersResultSet.close();
            correctAnswersResultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return xmlBuilder.toString();
    }

    public static void main(String[] args) {
        int questionId = 43; // Replace with the desired question ID

        MultipleChoice mc = new MultipleChoice(questionId);
        String xmlString = mc.generateMoodleXML();

        System.out.println(xmlString);
    }
}

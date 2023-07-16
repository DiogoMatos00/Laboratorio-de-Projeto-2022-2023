package dbToXML;
import Controller.connection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(List<String> idsDB, String path) {
        Connection conn = connection.connect();

        ResultSet rs = null;

        StringBuilder xmlBuilder = new StringBuilder();

        String RFinal = "<quiz>";

        for (String id : idsDB) {
            try {
                rs = connection.execute_query(
                        conn, String.format(
                                "SELECT TYPE FROM QUESTION WHERE QUESTIONID = '%s';", id));

            } catch (Exception SQLException) {
                System.out.println(SQLException);
                connection.disconnect(conn);
            }

            String result = null;
            try {
                if (rs.next()) {
                    String value = rs.getObject(1).toString();

                    if (value.equals("Correspondência de Colunas")) {

                        Match match = new Match(Integer.parseInt(id));
                        String matchXmlString = match.generateMoodleXML();
                        RFinal += matchXmlString;
                        System.out.println("Match XML:\n" + matchXmlString);

                    } else if (value.equals("Desenvolvimento")) {

                        Essay essay = new Essay(Integer.parseInt(id));
                        String essayXmlString = essay.generateMoodleXML();
                        RFinal += essayXmlString;
                        System.out.println("Essay XML:\n" + essayXmlString);

                    } else if (value.equals("Resposta Curta")) {

                        ShortAnswer shortAnswer = new ShortAnswer(Integer.parseInt(id));
                        String shortAnswerXmlString = shortAnswer.generateMoodleXML();
                        RFinal += shortAnswerXmlString;
                        System.out.println("Short Answer XML:\n" + shortAnswerXmlString);

                    } else if (value.equals("Múltipla escolha") || value.equals("Escolha múltipla")) {

                        MultipleChoice multipleChoice = new MultipleChoice(Integer.parseInt(id));
                        String multipleChoiceXmlString = multipleChoice.generateMoodleXML();
                        RFinal += multipleChoiceXmlString;
                        System.out.println("Multiple Choice XML:\n" + multipleChoiceXmlString);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        RFinal += "</quiz>";

        String outputFilePath = "data/" + path + ".xml";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, StandardCharsets.UTF_8))) {
            writer.write(RFinal);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
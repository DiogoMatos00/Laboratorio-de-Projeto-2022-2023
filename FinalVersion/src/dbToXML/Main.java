package dbToXML;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(List<String> idsDB, String path) {
        String essayFilePath = "DevelopmentQuery.xml";
        String matchFilePath = "MatchQuery.xml";
        String shortAnswerFilePath = "ShortAnswerQuery.xml";
        String multipleChoiceFilePath = "MultipleChoiceQuery.xml";

        Map<Integer, String> combinedQuestions = AllQuestionsMap.combineQuestions(
                essayFilePath,
                matchFilePath,
                shortAnswerFilePath,
                multipleChoiceFilePath
        );

        // Print the combined questions
        for (Map.Entry<Integer, String> entry : combinedQuestions.entrySet()) {
            int questionId = entry.getKey();
            String moodleXML = entry.getValue();
            /* System.out.println("Question ID: " + questionId);
            System.out.println(moodleXML); */
        }

        // Get input IDs and print the corresponding XML questions
        List<String> ids = idsDB;

        StringBuilder selectedQuestionsXML = new StringBuilder();

        for (String id : ids) {
            int questionId = Integer.parseInt(id);
            String moodleXML = combinedQuestions.get(questionId);

            if (moodleXML != null) {
                selectedQuestionsXML.append(moodleXML).append("\n\n");
            } else {
                System.out.println("Question ID " + questionId + " not found.");
            }
        }

        // Write the selected questions to a new XML file
        String outputFilePath = "data/" + path + ".xml";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, StandardCharsets.UTF_8))) {
            writer.write("<quiz>\n");
            writer.write(selectedQuestionsXML.toString());
            writer.write("</quiz>");
            System.out.println("Selected questions have been imported to " + outputFilePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
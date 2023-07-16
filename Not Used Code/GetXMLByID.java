package dbToXML;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class GetXMLByID {
    public static void buildQuiz(Map<Integer, String> questionMap, int[] questionIds, String outputFilePath) {
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            for (int questionId : questionIds) {
                if (questionMap.containsKey(questionId)) {
                    String questionXML = questionMap.get(questionId);
                    writer.write(questionXML + "\n\n");
                } else {
                    System.out.println("Question ID " + questionId + " not found.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
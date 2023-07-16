package dbToXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleChoice {
    public static void main(String[] args) {
        String xmlFilePath = "MultipleChoiceQuery.xml"; // Replace with the path to your XML file
        Map<Integer, String> moodleXMLMap = processAllQuestions(xmlFilePath);

        // Print moodleXMLMap contents
        for (Map.Entry<Integer, String> entry : moodleXMLMap.entrySet()) {
            int questionId = entry.getKey();
            String moodleXML = entry.getValue();

            System.out.println("Question ID: " + questionId);
            System.out.println(moodleXML);
            System.out.println();
        }

        int questionId = 60; // Specify the question ID for which you want to retrieve the Moodle XML
        String moodleXML = moodleXMLMap.get(questionId);

        if (moodleXML != null) {
            System.out.println("Moodle XML for Question " + questionId + ":\n" + moodleXML);
            // Uncomment the following line to save the Moodle XML to a file
            // saveMoodleXMLToFile(moodleXML, "GeneratedQuestion.xml");
        } else {
        System.out.println("Question ID " + questionId + " not found.");
        }
    }   

    public static Map<Integer, List<String>> parseXMLData(File file) {
        Map<Integer, List<String>> questionMap = new HashMap<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList queryNodes = document.getElementsByTagName("query");
            for (int i = 0; i < queryNodes.getLength(); i++) {
                Element queryElement = (Element) queryNodes.item(i);
                int questionId = Integer.parseInt(queryElement.getElementsByTagName("questionId").item(0).getTextContent());
                String description = queryElement.getElementsByTagName("description").item(0).getTextContent();
                String answer = queryElement.getElementsByTagName("answer").item(0).getTextContent();
                int val = Integer.parseInt(queryElement.getElementsByTagName("val").item(0).getTextContent());
                boolean isCorrect = val == 1;

                String answerLabel = isCorrect ? "Correct" : "Incorrect";

                List<String> questionData = questionMap.getOrDefault(questionId, new ArrayList<>());
                questionData.add("Description: " + description);
                questionData.add("Answer: " + answer + " (" + answerLabel + ")");
                questionMap.put(questionId, questionData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionMap;
    }

    public static String generateMoodleXML(int questionId, Map<Integer, List<String>> questionMap) {
        List<String> questionData = questionMap.get(questionId);
        if (questionData == null) {
            return ""; // Question ID not found in the parsed data
        }

        String description = "";
        List<String> answers = new ArrayList<>();
        int correctAnswersCount = 0;

        for (String data : questionData) {
            if (data.startsWith("Description: ")) {
                description = data.substring(13);
            } else if (data.startsWith("Answer: ")) {
                String answer = data.substring(8);
                boolean isCorrect = answer.endsWith("(Correct)");
                if (isCorrect) {
                    answer = answer.substring(0, answer.length() - 10); // Remove the "(Correct)" suffix
                    correctAnswersCount++;
                } else {
                    answer = answer.substring(0, answer.length() - 12); // Remove the "(Incorrect)" suffix
                }
                answers.add(answer + (isCorrect ? " (Correct)" : ""));
            }
        }

        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<question type=\"multichoice\">\n");
        xmlBuilder.append("  <name>\n");
        xmlBuilder.append("    <text>Question ").append(questionId).append("</text>\n");
        xmlBuilder.append("  </name>\n");
        xmlBuilder.append("  <questiontext format=\"html\">\n");
        xmlBuilder.append("    <text><![CDATA[").append(description).append("]]></text>\n");
        xmlBuilder.append("  </questiontext>\n");

        for (String answer : answers) {
            boolean isCorrect = answer.endsWith("(Correct)");
            double fraction = isCorrect ? 100.0 / correctAnswersCount : 0.0;

            xmlBuilder.append("  <answer fraction=\"").append(fraction).append("\">\n");
            xmlBuilder.append("    <text><![CDATA[").append(answer.replace(" (Correct)", "")).append("]]></text>\n");
            xmlBuilder.append("    <feedback>\n");
            xmlBuilder.append("      <text>").append(isCorrect ? "Correct!" : "Incorrect!").append("</text>\n");
            xmlBuilder.append("    </feedback>\n");
            xmlBuilder.append("  </answer>\n");
        }

        xmlBuilder.append("</question>");

        return xmlBuilder.toString();
    }

    public static void saveMoodleXMLToFile(String xml, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(xml);
            System.out.println("Moodle XML saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, String> processAllQuestions(String xmlFilePath) {
    File file = new File(xmlFilePath);
    Map<Integer, List<String>> questionMap = parseXMLData(file);
    Map<Integer, String> moodleXMLMap = new HashMap<>();

    for (Map.Entry<Integer, List<String>> entry : questionMap.entrySet()) {
        int questionId = entry.getKey();
        String moodleXML = generateMoodleXML(questionId, questionMap);
        moodleXMLMap.put(questionId, moodleXML);
    }

    return moodleXMLMap;
}
}

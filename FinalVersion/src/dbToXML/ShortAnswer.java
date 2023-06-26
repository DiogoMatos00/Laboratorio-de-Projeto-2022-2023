package dbToXML;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ShortAnswer {
    public static void main(String[] args) {
        String originalXmlFilePath = "ShortAnswerQuery.xml";
        Map<String, String> questionMap = convertToMoodleXML(originalXmlFilePath);

        // Print the questionMap contents
        for (Map.Entry<String, String> entry : questionMap.entrySet()) {
            String questionId = entry.getKey();
            String moodleXML = entry.getValue();
           /*  System.out.println("Question ID: " + questionId);
            System.out.println(moodleXML); */
        }
    }

    public static Map<String, String> convertToMoodleXML(String originalXmlFilePath) {
        Map<String, String> questionMap = new HashMap<>();

        try {
            // Parse the input XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document inputDoc = builder.parse(new File(originalXmlFilePath));

            // Get the list of "query" elements
            NodeList queryList = inputDoc.getElementsByTagName("query");

            // Process each "query" element
            for (int i = 0; i < queryList.getLength(); i++) {
                Element queryElement = (Element) queryList.item(i);

                // Extract the question ID, description, and answer placeholder
                int questionId = Integer.parseInt(queryElement.getElementsByTagName("questionId").item(0).getTextContent());
                String description = queryElement.getElementsByTagName("description").item(0).getTextContent();
                String answerPlaceholder = queryElement.getElementsByTagName("answerPlaceholder").item(0).getTextContent();

                // Generate the Moodle XML for the question
                String moodleXML = generateMoodleXML(questionId, description, answerPlaceholder);

                // Add the Moodle XML to the questionMap
                questionMap.put(String.valueOf(questionId), moodleXML);
            }

            /* System.out.println("Moodle XML generated successfully."); */
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionMap;
    }

    public static String generateMoodleXML(int questionId, String description, String answerPlaceholder) {
        StringBuilder xmlBuilder = new StringBuilder();
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
        xmlBuilder.append("</question>");

        return xmlBuilder.toString();
    }
}

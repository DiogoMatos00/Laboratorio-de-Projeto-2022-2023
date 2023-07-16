package dbToXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Match {
    public static void main(String[] args) {
        String originalXmlFilePath = "MatchQuery.xml";
        Map<String, String> questionMap = convertToMoodleXML(originalXmlFilePath);

        // Print the questionMap contents
        for (Map.Entry<String, String> entry : questionMap.entrySet()) {
            String questionId = entry.getKey();
            String moodleXML = entry.getValue();
            /* System.out.println("Question ID: " + questionId);
            System.out.println(moodleXML); */
        }
    }

    public static Map<String, String> convertToMoodleXML(String originalXmlFilePath) {
        Map<String, String> questionMap = new HashMap<>();

        try {
            // Parse the input XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document originalDoc = builder.parse(new File(originalXmlFilePath));

            // Retrieve the rows from the original XML
            NodeList rowList = originalDoc.getElementsByTagName("row");

            // Process each row
            for (int i = 0; i < rowList.getLength(); i++) {
                Element rowElement = (Element) rowList.item(i);

                // Retrieve the left side and right side values
                String questionId = getTextContentByTagName(rowElement, "QuestionId");
                String leftSide = getTextContentByTagName(rowElement, "LeftSide");
                String rightSide = getTextContentByTagName(rowElement, "RightSide");

                // Generate the Moodle XML for the question
                String moodleXML = generateMoodleXML(questionId, leftSide, rightSide);

                // Add the Moodle XML to the questionMap
                questionMap.put(questionId, moodleXML);
            }

            /* System.out.println("Moodle XML generated successfully."); */
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionMap;
    }

    public static String generateMoodleXML(String questionId, String leftSide, String rightSide) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<question type=\"matching\">\n");
        xmlBuilder.append("  <name>\n");
        xmlBuilder.append("    <text>Question ").append(questionId).append("</text>\n");
        xmlBuilder.append("  </name>\n");
        xmlBuilder.append("  <questiontext format=\"html\">\n");
        xmlBuilder.append("    <text>Sobre a classificação dos riscos em um sistema crítico, faça a correspondência:</text>\n");
        xmlBuilder.append("  </questiontext>\n");
        xmlBuilder.append("  <subquestion format=\"html\">\n");
        xmlBuilder.append("    <text>").append(leftSide).append("</text>\n");
        xmlBuilder.append("    <answer>\n");
        xmlBuilder.append("      <text>").append(rightSide).append("</text>\n");
        xmlBuilder.append("    </answer>\n");
        xmlBuilder.append("  </subquestion>\n");
        xmlBuilder.append("</question>");

        return xmlBuilder.toString();
    }

    private static String getTextContentByTagName(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}

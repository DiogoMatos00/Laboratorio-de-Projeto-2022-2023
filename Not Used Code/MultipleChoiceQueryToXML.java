package dbToXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class
MultipleChoiceQueryToXML {
    private static final String URL = "jdbc:mysql://localhost:3306/projeto";
    private static final String USERNAME = "teste";
    private static final String PASSWORD = "Adminadmin123";

    public static void main() {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Fetch data from the database
            String query = "SELECT q.QuestionId, q.Description, mc.Answer, mc.Val, mc.TrueOrFalse " +
                    "FROM Question q " +
                    "JOIN MultipleChoice mc ON mc.QuestionId = q.QuestionId " +
                    "WHERE q.Type = 'multipla escolha' " +
                    "UNION " +
                    "SELECT q.QuestionId, q.Description, mc.Answer, mc.Val, mc.TrueOrFalse " +
                    "FROM Question q " +
                    "JOIN MultipleChoice mc ON mc.QuestionId = q.QuestionId " +
                    "WHERE q.Type = 'Escolha multipla'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Create a new Document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Create the root element of the XML tree
            Element rootElement = doc.createElement("queries");
            doc.appendChild(rootElement);

            while (resultSet.next()) {
                // Create a child element for each row in the result set
                Element queryElement = doc.createElement("query");
                rootElement.appendChild(queryElement);

                // Create sub-elements for the columns in the result set
                Element questionIdElement = doc.createElement("questionId");
                questionIdElement.appendChild(doc.createTextNode(resultSet.getString("QuestionId")));
                queryElement.appendChild(questionIdElement);

                Element descriptionElement = doc.createElement("description");
                descriptionElement.appendChild(doc.createTextNode(resultSet.getString("Description")));
                queryElement.appendChild(descriptionElement);

                Element answerElement = doc.createElement("answer");
                answerElement.appendChild(doc.createTextNode(resultSet.getString("Answer")));
                queryElement.appendChild(answerElement);

                Element valElement = doc.createElement("val");
                valElement.appendChild(doc.createTextNode(resultSet.getString("Val")));
                queryElement.appendChild(valElement);

                Element trueOrFalseElement = doc.createElement("trueOrFalse");
                trueOrFalseElement.appendChild(doc.createTextNode(resultSet.getString("TrueOrFalse")));
                queryElement.appendChild(trueOrFalseElement);

                // Add a space between each query
                Text spaceText = doc.createTextNode("\n");
                rootElement.appendChild(spaceText);
            }

            // Configure the transformer to remove the XML declaration
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            // Write the XML content to a file


            File file = new File("MultipleChoiceQuery.xml");
            FileOutputStream fos = new FileOutputStream(file);
            StreamResult result = new StreamResult(fos);
            transformer.transform(new DOMSource(doc), result);

            fos.close();
            System.out.println("XML file saved successfully!");

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

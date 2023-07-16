package dbToXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.*;

public class EssayQueryToXML {
    private static final String URL = "jdbc:mysql://localhost:3306/projeto";
    private static final String USERNAME = "teste";
    private static final String PASSWORD = "Adminadmin123";

    public static void main() {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Fetch data from the database
            String query = "SELECT q.QuestionId, q.Description, d.Answer_Placeholder " +
                    "FROM Question q " +
                    "JOIN Development d ON d.QuestionId = q.QuestionId " +
                    "WHERE q.Type = 'Desenvolvimento'";

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

                Element answerPlaceholderElement = doc.createElement("answerPlaceholder");
                answerPlaceholderElement.appendChild(doc.createTextNode(resultSet.getString("Answer_Placeholder")));
                queryElement.appendChild(answerPlaceholderElement);

                // Add a space between each query
                Text spaceText = doc.createTextNode("\n");
                rootElement.appendChild(spaceText);
            }

            // Write the XML content to a file
            File file = new File("DevelopmentQuery.xml");
            FileOutputStream fos = new FileOutputStream(file);
            Writer writer = new OutputStreamWriter(fos, "UTF-8");

            javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new javax.xml.transform.dom.DOMSource(doc),
                    new javax.xml.transform.stream.StreamResult(writer));

            writer.close();
            System.out.println("XML file saved successfully!");

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | ParserConfigurationException | IOException |
                javax.xml.transform.TransformerException e) {
            e.printStackTrace();
        }
    }
}


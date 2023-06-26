package dbToXML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class MatchQueryToXML {
    public static void main() {
        try {

            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto", "teste", "Adminadmin123");
            Statement statement = connection.createStatement();

            // Execute the SQL query
            String sqlQuery = "SELECT mf.*, q.* FROM MatchFollowing mf JOIN Question q ON mf.QuestionId = q.QuestionId WHERE q.type = 'correspondência'";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Create a new XML document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Create the root element
            Element rootElement = doc.createElement("data");
            doc.appendChild(rootElement);

            // Process the query results
            while (resultSet.next()) {
                // Create a new row element
                Element rowElement = doc.createElement("row");
                rootElement.appendChild(rowElement);

                // Add the columns as child elements to the row
                Element leftSideElement = doc.createElement("LeftSide");
                Text leftSideText = doc.createTextNode(resultSet.getString("LeftSide"));
                leftSideElement.appendChild(leftSideText);
                rowElement.appendChild(leftSideElement);

                Element rightSideElement = doc.createElement("RightSide");
                Text rightSideText = doc.createTextNode(resultSet.getString("RightSide"));
                rightSideElement.appendChild(rightSideText);
                rowElement.appendChild(rightSideElement);

                Element questionIdElement = doc.createElement("QuestionId");
                Text questionIdText = doc.createTextNode(resultSet.getString("QuestionId"));
                questionIdElement.appendChild(questionIdText);
                rowElement.appendChild(questionIdElement);

                // Add more columns as needed
            }

            // Prepare the transformer to remove the XML declaration
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, StandardCharsets.UTF_8.name());

            // Convert the DOM document to an XML string
            DOMSource source = new DOMSource(doc);
            OutputStream outputStream = new FileOutputStream(new File("MatchQuery.xml"));
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
            outputStream.close();

            System.out.println("XML file generated successfully.");

        } catch (SQLException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

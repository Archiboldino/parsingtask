package util.xml;

import entity.Person;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * DomXmlParser
 * created on 6/9/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
public class DomXmlParser implements XmlParser {
    @Override
    public List<Person> parsePersons(String fileName) throws IOException {
        List<Person> people = new ArrayList<>();
        File xmlFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList personElements = doc.getElementsByTagName("person");
            for (int i = 0; i < personElements.getLength(); i++) {
                Node node = personElements.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Person p = new Person(Integer.parseInt(element.getAttribute("id")),
                            element.getElementsByTagName("name").item(0).getTextContent(),
                            element.getElementsByTagName("address").item(0).getTextContent(),
                            Integer.parseInt(element.getElementsByTagName("cash").item(0).getTextContent()),
                            element.getElementsByTagName("education").item(0).getTextContent());
                    people.add(p);
                }
            }
        } catch (ParserConfigurationException | SAXException | NumberFormatException e) {
            e.printStackTrace();
        }

        return people;
    }

    @Override
    public String getPersonsXml(List<Person> content) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            doc.setXmlStandalone(true);

            Element rootElement = doc.createElement("catalog");
            doc.appendChild(rootElement);

            Element notebook = doc.createElement("notebook");
            rootElement.appendChild(notebook);

            for (Person p : content) {
                Element personElement = doc.createElement("person");

                Attr attrId = doc.createAttribute("id");
                attrId.setValue(p.getId().toString());
                personElement.setAttributeNode(attrId);

                Element nameElement = doc.createElement("name");
                nameElement.setTextContent(p.getName());
                personElement.appendChild(nameElement);

                Element addresElement = doc.createElement("address");
                addresElement.setTextContent(p.getAddress());
                personElement.appendChild(addresElement);

                Element cashElement = doc.createElement("cash");
                cashElement.setTextContent(p.getCash().toString());
                personElement.appendChild(cashElement);

                Element educationElement = doc.createElement("education");
                educationElement.setTextContent(p.getEducation());
                personElement.appendChild(educationElement);

                notebook.appendChild(personElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StringWriter result = new StringWriter();
            transformer.transform(source, new StreamResult(result));

            return result.toString();
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            return "";
        }
    }
}

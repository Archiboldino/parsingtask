package util;

import entity.Person;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.print.Doc;
import java.util.List;

public class XmlParser {
    public static String parse() {
        Document doc = new Document();
        

        return "";
    }

    public static String getXml(List<Person> content) {
        Element catalog = new Element("catalog");
        Element notebook = new Element("notebook");

        catalog.addContent(notebook);

        content.forEach(p -> catalog.addContent(createPersonElement(p)));

        Document doc = new Document();
        doc.addContent(catalog);

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());

        return xmlOutput.outputString(doc);
    }

    public static Element createPersonElement(Person person) {
        Element personRoot = new Element("person");
        personRoot.setAttribute("id", person.getId().toString());

        Element nameElement = new Element("name");
        nameElement.setText(person.getName());

        Element addressElement = new Element("address");
        addressElement.setText(person.getAddress());

        Element cashElement = new Element("cash");
        cashElement.setText(person.getCash().toString());

        Element educationElement = new Element("education");
        educationElement.setText(person.getEducation());

        personRoot.addContent(nameElement);
        personRoot.addContent(addressElement);
        personRoot.addContent(cashElement);
        personRoot.addContent(educationElement);

        return personRoot;
    }
}

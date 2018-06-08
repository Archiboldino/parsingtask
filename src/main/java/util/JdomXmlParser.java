package util;

import entity.Person;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JdomXmlParser implements XmlParser{

    @Override
    public List<Person> parsePersons(String fileName) throws JDOMException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        SAXBuilder builder;
        try {
            builder = new SAXBuilder();
            Document document = builder.build(new File(fileName));

            Element root = document.getRootElement();

            return root.getChild("notebook").getChildren("person").stream()
                    .map(this::createPersonFromElement)
                    .collect(Collectors.toList());
        } catch ( IOException | JDOMException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Person createPersonFromElement(Element element) {
        return new Person(Integer.parseInt(element.getAttributeValue("id")),
                element.getChildText("name"),
                element.getChildText("address"),
                Integer.parseInt(element.getChildText("cash")),
                element.getChildText("education"));
    }


    @Override
    public String getPersonsXml(List<Person> content) {
        Element catalog = new Element("catalog");
        Element notebook = new Element("notebook");

        catalog.addContent(notebook);

        content.forEach(p -> notebook.addContent(createPersonElement(p)));

        Document doc = new Document();
        doc.addContent(catalog);

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());

        return xmlOutput.outputString(doc);
    }

    private Element createPersonElement(Person person) {
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

package util;

import entity.Person;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XmlParser {
    public static String parse() {

        return "";
    }

    public static void write() {
        Element catalog = new Element("catalog");
        Element notebook = new Element("notebook");
        catalog.setContent(notebook);
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

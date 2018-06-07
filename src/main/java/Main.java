import entity.Person;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import util.XmlParser;

public class Main {
    public static void main(String[] args) {
        Element personElement = XmlParser.createPersonElement(new Person(1, "asd", "123", 123, "sa"));
        Document doc = new Document();
        doc.addContent(personElement);

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        String output = xmlOutput.outputString(doc);


        System.out.println(output);
    }
}

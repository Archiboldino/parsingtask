package util.xml;

import entity.Person;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.List;

/**
 * XmlParser
 * created on 6/8/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
public interface XmlParser {

    List<Person> parsePersons(String fileName) throws JDOMException, IOException;

    String getPersonsXml(List<Person> content);
}

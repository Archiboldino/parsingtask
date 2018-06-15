package service;

import entity.Person;
import org.jdom2.JDOMException;
import util.xml.DomXmlParser;
import util.xml.JdomXmlParser;
import util.xml.XmlParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * XmlService
 * created on 6/10/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
public class XmlService {
    private XmlParser parser;

    public XmlService(XmlParser parser) {
        this.parser = parser;
    }

    public void writePersonsToFile(String path) {
        List<Person> persons = Arrays.asList(new Person(1, "asd", "123", 123, "sa"),
                new Person(2, "dsa", "addr", 321, "as"));

        try (FileOutputStream stream = new FileOutputStream(path)) {
            stream.write(parser.getPersonsXml(persons).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filterPersonsAndWrite(String fromFile, String toFile) throws JDOMException, IOException {
        List<Person> people = parser.parsePersons(getClass().getResource(fromFile).getFile());
        List<Person> filteredPeople = people.stream()
                .filter(p -> p.getCash() > 10000)
                .collect(Collectors.toList());
        filteredPeople.forEach(System.out::println);

        try (FileOutputStream stream = new FileOutputStream(toFile)) {
            stream.write(parser.getPersonsXml(filteredPeople).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

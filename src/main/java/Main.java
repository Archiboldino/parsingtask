import entity.Person;
import org.jdom2.JDOMException;
import util.JdomXmlParser;
import util.XmlParser;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws JDOMException, IOException {
        filterPersonsAndWrite();
    }

    private static void writePersonsToFile() {
        List<Person> persons = Arrays.asList(new Person(1, "asd", "123", 123, "sa"),
            new Person(2, "dsa", "addr", 321, "as"));
        XmlParser parser = new JdomXmlParser();

        try (FileOutputStream stream = new FileOutputStream("kek.xml")) {
            stream.write(parser.getPersonsXml(persons).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void filterPersonsAndWrite() throws JDOMException, IOException {
        XmlParser parser = new JdomXmlParser();
        List<Person> people = parser.parsePersons(Main.class.getResource("kek.xml").getFile());
        List<Person> filteredPeople = people.stream()
                .filter(p -> p.getCash() > 10000)
                .collect(Collectors.toList());
        filteredPeople.forEach(System.out::println);

        try (FileOutputStream stream = new FileOutputStream("new.xml")) {
            stream.write(parser.getPersonsXml(filteredPeople).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

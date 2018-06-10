import entity.Currency;
import entity.Person;
import org.jdom2.JDOMException;
import org.junit.Assert;
import util.json.JacksonJSONParser;
import util.xml.DomXmlParser;
import util.xml.JdomXmlParser;
import util.xml.XmlParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        JacksonJSONParser parser = new JacksonJSONParser();
        List<Currency> c = parser.fromJSON(
                new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json").openStream());
    }

    private static void writePersonsToFile() {
        List<Person> persons = Arrays.asList(new Person(1, "asd", "123", 123, "sa"),
            new Person(2, "dsa", "addr", 321, "as"));
        XmlParser parser = new JdomXmlParser();

        try (FileOutputStream stream = new FileOutputStream("file.xml")) {
            stream.write(parser.getPersonsXml(persons).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void filterPersonsAndWrite() throws JDOMException, IOException {
        XmlParser parser = new DomXmlParser();
        List<Person> people = parser.parsePersons(Main.class.getResource("file.xml").getFile());
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

package util.xml;

import entity.Person;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.xml.DomXmlParser;
import util.xml.JdomXmlParser;
import util.xml.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * XmlParserTest
 * created on 6/8/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class XmlParserTest {
    private XmlParser parser;

    private List<Person> people;

    public XmlParserTest(XmlParser parser) {
        this.parser = parser;

        people = new ArrayList<>();
        people.add(new Person(1, "asd", "123", 123, "sa"));
        people.add(new Person(2, "dsa", "addr", 1000000, "as"));
    }

    @Test
    public void parsePersons() throws JDOMException, IOException {
        List<Person> actual = parser.parsePersons(getClass().getResource("/test_expected.xml").getFile());

        Assert.assertEquals(people, actual);
    }

    @Test
    public void getPersonsXml() throws IOException {
        //Remove formatting
        String actual = parser.getPersonsXml(people)
                .replaceAll("\\n\\r", "").replaceAll("[\\t\\s]", "");

        String expected = new String(Files.readAllBytes(Paths.get(getClass()
                .getResource("/test_expected.xml").getFile())))
                .replaceAll("\\n\\r", "").replaceAll("[\\t\\s]", "");

        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static Collection<Object> parameters() {
        return Arrays.asList(new Object[]{new JdomXmlParser()},
                new Object[]{new DomXmlParser()});
    }
}
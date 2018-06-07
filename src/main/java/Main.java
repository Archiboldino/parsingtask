import entity.Person;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import util.XmlParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person(1, "asd", "123", 123, "sa"),
            new Person(2, "dsa", "addr", 321, "as"));

        try (FileOutputStream stream = new FileOutputStream("kek.xml")) {
            stream.write(XmlParser.getXml(persons).getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

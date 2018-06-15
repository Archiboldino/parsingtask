package util.json;

import entity.Currency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * JacksonJSONParserTest
 * created on 6/10/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
public class JSONParserTest {
    List<Currency> currencies;
    String expectedJSON;

    @Before
    public void setup() throws IOException {
        currencies = Arrays.asList(new Currency(986, "Бразильський реал", 7.005109f, "BRL", LocalDate.of(2018, 6, 10)),
                new Currency(972, "Сомонi", 2.897071f, "TJS", LocalDate.of(2018, 6, 10)));

        expectedJSON = new String(Files.readAllBytes(Paths.get(getClass().
                getResource("/test.json").getFile())));
    }

    @Test
    public void toJSON() throws IOException {
        JSONParser parser = new JacksonJSONParser();

        String actual = parser.toJSON(currencies);

        Assert.assertEquals(expectedJSON, actual);
    }

    @Test
    public void fromJSON() throws FileNotFoundException {
        JSONParser parser = new JacksonJSONParser();

        List<Currency> actual = parser.fromJSON(new FileInputStream(getClass().
                getResource("/test.json").getFile()));

        Assert.assertEquals(currencies, actual);
    }
}
package util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

/**
 * JacksonJSONParser
 * created on 6/9/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
public class JacksonJSONParser implements JSONParser {
    @Override
    public String toJSON(List<Currency> currencies) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter result = new StringWriter();
        try {
            mapper.writeValue(result, currencies);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    @Override
    public List<Currency> fromJSON(InputStream stream) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(stream,
                    new TypeReference<List<Currency>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

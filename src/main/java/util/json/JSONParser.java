package util.json;

import entity.Currency;

import java.io.InputStream;
import java.util.List;

/**
 * JSONParser
 * created on 6/10/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
public interface JSONParser {
    String toJSON(List<Currency> currencies);

    List<Currency> fromJSON(InputStream stream);
}

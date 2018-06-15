package controller;

import org.jdom2.JDOMException;
import service.XmlService;
import util.xml.XmlParser;

import java.io.IOException;

/**
 * XMLController
 * created on 6/10/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
public class XMLController {
    private XmlService service;
    private static final String FILE_NAME = "new.xml";
    private static final String RESOURCE_NAME = "file.xml";

    public XMLController(XmlParser parser) {
        service = new XmlService(parser);
    }

    public void processXml() {
        try {
            service.filterPersonsAndWrite(getClass().getResource(RESOURCE_NAME).getFile(), FILE_NAME);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

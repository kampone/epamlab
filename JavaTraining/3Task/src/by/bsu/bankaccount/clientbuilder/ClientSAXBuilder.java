package by.bsu.bankaccount.clientbuilder;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import sun.rmi.runtime.Log;

import java.io.IOException;

/**
 * Created by note on 11.03.2015.
 */
public class ClientSAXBuilder extends AbstractClientBuilder {
    private static final Logger LOG = Logger.getLogger(ClientSAXBuilder.class);
    private ClientXMLHandler handler;
    private XMLReader reader;

    public ClientSAXBuilder() {
        handler = new ClientXMLHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void buildListClient(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        clients = handler.getClients();
    }
}

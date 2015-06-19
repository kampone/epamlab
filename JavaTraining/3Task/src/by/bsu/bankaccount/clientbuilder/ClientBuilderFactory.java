package by.bsu.bankaccount.clientbuilder;

import org.apache.log4j.Logger;

/**
 * Created by note on 16.03.2015.
 */
public class ClientBuilderFactory {
    private final static Logger LOG = Logger.getLogger(ClientBuilderFactory.class);
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public AbstractClientBuilder createClientBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new ClientDOMBuilder();
            case STAX:
                return new ClientStAXBuilder();
            case SAX:
                return new ClientSAXBuilder();
            default:
                LOG.error("No such enum");
                return null;
        }
    }
}
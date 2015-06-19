package by.bsu.bankaccount.clientbuilder;

import by.bsu.bankaccount.account.CreditAccount;
import by.bsu.bankaccount.account.CurrencyEnum;
import by.bsu.bankaccount.account.DepositAccount;
import by.bsu.bankaccount.account.PersonalAccount;
import by.bsu.bankaccount.client.Client;
import by.bsu.bankaccount.exception.LogicException;
import by.bsu.bankaccount.exception.TechnicException;
import by.bsu.bankaccount.exception.XMLTechniсException;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by note on 10.03.2015.
 */
public class ClientStAXBuilder extends AbstractClientBuilder {
    private final static Logger LOG = Logger.getLogger(ClientStAXBuilder.class);
    private XMLInputFactory inputFactory;

    public ClientStAXBuilder() {
        super();
        inputFactory = XMLInputFactory.newFactory();
    }

    @Override
    public void buildListClient(String filename) {
        XMLStreamReader reader = null;
        String text = null;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))){

            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT) {
                    text = reader.getLocalName();
                    if (ClientEnum.valueOf(text.toUpperCase().replaceAll("-","")) == ClientEnum.CLIENT) {
                        Client client = null;
                        try {
                            client = buildClient(reader);
                        } catch (TechnicException e) {
                            e.printStackTrace();
                        }
                        clients.add(client);
                    }
                }
            }
        } catch (XMLStreamException | IOException | XMLTechniсException | LogicException e) {
            LOG.error(e);
        }
    }

    private Client buildClient(XMLStreamReader reader) throws XMLTechniсException, LogicException, TechnicException {
        Client client = new Client();
        if(reader.getAttributeValue(0) != null) {
            client.setId(idFromString(reader.getAttributeValue(0)));
        }
        String text;
        try {
            while (reader.hasNext()){
                int type = reader.next();
                switch (type){
                    case XMLStreamConstants.START_ELEMENT:
                        text = reader.getLocalName();
                        switch (ClientEnum.valueOf(text.toUpperCase().replaceAll("-",""))){
                            case FIRSTNAME:
                                client.setFirstName(getXMLText(reader));
                                break;
                            case LASTNAME:
                                client.setSecondName(getXMLText(reader));
                                break;
                            case PERSONALACCOUNT:
                                client.addAccount(buildPersonalAccount(reader));
                                break;
                            case DEPOSITACCOUNT:
                                client.addAccount(buildDepositAccount(reader));
                                break;
                            case CREDITACCOUNT:
                                client.addAccount(buildCreditAccount(reader));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        text = reader.getLocalName();
                        if(ClientEnum.valueOf(text.toUpperCase().replaceAll("-","")) == ClientEnum.CLIENT){
                            return client;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            throw new XMLTechniсException(e);
        }
        throw new XMLTechniсException("Unknown element in tag Client");
    }

    private PersonalAccount buildPersonalAccount(XMLStreamReader reader) throws LogicException, XMLTechniсException, TechnicException {
        PersonalAccount account = new PersonalAccount();
        if(reader.getAttributeValue(0) != null) {
            account.setId(idFromString(reader.getAttributeValue(0)));
        }
        try {
            while (reader.hasNext()){
                int type = reader.next();
                String text;
                switch (type){
                    case XMLStreamConstants.START_ELEMENT:
                        text = reader.getLocalName();
                        switch (ClientEnum.valueOf(text.toUpperCase().replaceAll("-",""))){
                            case CURRENCY:
                                account.setCurrency(CurrencyEnum.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case ISBLOCKED:
                                account.setBlocked(Boolean.parseBoolean(getXMLText(reader)));
                                break;
                            case UPDATEDATE:
                                account.setUpdateDate(parseDate(getXMLText(reader)));
                                break;
                            case BALANCE:
                                account.setBalance(Double.parseDouble(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        text = reader.getLocalName();
                        if(ClientEnum.valueOf(text.toUpperCase().replaceAll("-","")) == ClientEnum.PERSONALACCOUNT){
                            return account;
                        }
                        break;

                }

            }
        } catch (XMLStreamException e) {
           throw new  XMLTechniсException(e);
        }
        throw new XMLTechniсException("Unknown element in tag PersonalAccount");

    }

    private DepositAccount buildDepositAccount(XMLStreamReader reader) throws LogicException, XMLTechniсException, TechnicException {
        DepositAccount account = new DepositAccount();
        if(reader.getAttributeValue(0) != null) {
            account.setId(idFromString(reader.getAttributeValue(0)));
        }
        try {
            while (reader.hasNext()){
                int type = reader.next();
                String text;
                switch (type){
                    case XMLStreamConstants.START_ELEMENT:
                        text = reader.getLocalName();
                        switch (ClientEnum.valueOf(text.toUpperCase().replaceAll("-",""))){
                            case CURRENCY:
                                account.setCurrency(CurrencyEnum.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case ISBLOCKED:
                                account.setBlocked(Boolean.parseBoolean(getXMLText(reader)));
                                break;
                            case UPDATEDATE:
                                account.setUpdateDate(parseDate(getXMLText(reader)));
                                break;
                            case BALANCE:
                                account.setBalance(Double.parseDouble(getXMLText(reader)));
                                break;
                            case INTERESTRATE:
                                account.setInterestRate(Double.parseDouble(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        text = reader.getLocalName();
                        if(ClientEnum.valueOf(text.toUpperCase().replaceAll("-","")) == ClientEnum.DEPOSITACCOUNT){
                            return account;
                        }
                        break;

                }

            }
        } catch (XMLStreamException e) {
            throw new XMLTechniсException(e);
        }
        throw new XMLTechniсException("Unknown element in tag DepositAccount");

    }


    private CreditAccount buildCreditAccount(XMLStreamReader reader) throws LogicException, XMLTechniсException, TechnicException {
        CreditAccount account = new CreditAccount();
        if(reader.getAttributeValue(0) != null) {
            account.setId(idFromString(reader.getAttributeValue(0)));
        }
        try {
            while (reader.hasNext()){
                int type = reader.next();
                String text;
                switch (type){
                    case XMLStreamConstants.START_ELEMENT:
                        text = reader.getLocalName();
                        switch (ClientEnum.valueOf(text.toUpperCase().replaceAll("-",""))){
                            case CURRENCY:
                                account.setCurrency(CurrencyEnum.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case ISBLOCKED:
                                account.setBlocked(Boolean.parseBoolean(getXMLText(reader)));
                                break;
                            case UPDATEDATE:
                                account.setUpdateDate(parseDate(getXMLText(reader)));
                                break;
                            case BALANCE:
                                account.setBalance(Double.parseDouble(getXMLText(reader)));
                                break;
                            case LENDINGRATE:
                                account.setLendingRate(Double.parseDouble(getXMLText(reader)));
                                break;
                            case MONTH:
                                account.setMonth(Integer.parseInt(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        text = reader.getLocalName();
                        if(ClientEnum.valueOf(text.toUpperCase().replaceAll("-","")) == ClientEnum.CREDITACCOUNT){
                            return account;
                        }
                        break;

                }

            }
        } catch (XMLStreamException e) {
            throw new XMLTechniсException(e);
        }
        throw new XMLTechniсException("Unknown element in tag CreditAccount");

    }

    private String getXMLText(XMLStreamReader reader) throws XMLTechniсException {
        String text = null;
        try {
            if(reader.hasNext()){
                reader.next();
                text = reader.getText();
            }
        } catch (XMLStreamException e) {
            throw new XMLTechniсException(e);
        }
        return text;
    }

}

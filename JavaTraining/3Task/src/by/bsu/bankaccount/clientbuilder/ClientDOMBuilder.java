package by.bsu.bankaccount.clientbuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import by.bsu.bankaccount.account.*;
import by.bsu.bankaccount.client.Client;
import by.bsu.bankaccount.exception.LogicException;
import by.bsu.bankaccount.exception.TechnicException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by note on 10.03.2015.
 */
public class ClientDOMBuilder extends AbstractClientBuilder{
    private DocumentBuilder documentBuilder;
    private static final Logger LOG = Logger.getLogger(ClientDOMBuilder.class);

    public ClientDOMBuilder() {
        this.clients = new ArrayList<Client>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error("Ошибка конфигурации парсера:" + e);
        }

    }

    @Override
    public void buildListClient(String filename) {
        Document doc = null;
        try  {
            doc = documentBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList clientsList = root.getElementsByTagName("client");
            for (int i = 0; i < clientsList.getLength() ; i++) {
                Element clientElement = (Element) clientsList.item(i);
                Client client = buildClient(clientElement);
                clients.add(client);
            }
        } catch (SAXException | IOException | LogicException | TechnicException e) {
            LOG.error(e);
        }
    }

    private Client buildClient(Element clientElement) throws LogicException, TechnicException {
        Client client = new Client();
        if (clientElement.getAttribute("client-id") != null){
            client.setId(idFromString(clientElement.getAttribute("client-id")));
        }
        client.setFirstName(getElementTextContent(clientElement, "first-name"));
        client.setSecondName(getElementTextContent(clientElement, "last-name"));
        NodeList personalAccountList = clientElement.getElementsByTagName("personal-account");
        NodeList creditAccountList = clientElement.getElementsByTagName("credit-account");
        NodeList depositAccountList = clientElement.getElementsByTagName("deposit-account");
        for (int i = 0; i < personalAccountList.getLength(); i++) {
            AbstractAccount account = buildPersonalAccount((Element) personalAccountList.item(i));
            client.addAccount(account);
        }

        for (int i = 0; i < depositAccountList.getLength() ; i++) {
            AbstractAccount account = buildDepositAccount((Element) depositAccountList.item(i));
            client.addAccount(account);
        }

        for (int i = 0; i < creditAccountList.getLength(); i++) {
            AbstractAccount account = buildCreditAccount((Element) creditAccountList.item(i));
            client.addAccount(account);
        }
        return  client;
    }

    private AbstractAccount buildAbstractAccount(Element accountElement, AbstractAccount account) throws LogicException, TechnicException {
        if(accountElement.getAttribute("account-id") != null){
            account.setId(idFromString(accountElement.getAttribute("account-id")));
        }
        account.setCurrency(CurrencyEnum.valueOf(getElementTextContent(accountElement, "currency").toUpperCase()));
        account.setBlocked(Boolean.parseBoolean(getElementTextContent(accountElement, "is-blocked")));
        account.setBalance(Double.parseDouble(getElementTextContent(accountElement,"balance")));
        account.setUpdateDate(parseDate(getElementTextContent(accountElement, "update-date")));

            return account;
    }
    private PersonalAccount buildPersonalAccount(Element accountElement) throws LogicException, TechnicException {
        PersonalAccount account = new PersonalAccount();
        account = (PersonalAccount) buildAbstractAccount(accountElement, account);
        return account;
    }

    private CreditAccount buildCreditAccount(Element accountElement) throws LogicException, TechnicException {
        CreditAccount account = new CreditAccount();
        account = (CreditAccount) buildAbstractAccount(accountElement, account);
        account.setLendingRate(Double.parseDouble(getElementTextContent(accountElement,"lending-rate")));
        account.setMonth(Integer.parseInt(getElementTextContent(accountElement, "month")));
        return account;
    }

    private DepositAccount buildDepositAccount(Element accountElement) throws LogicException, TechnicException {
        DepositAccount account = new DepositAccount();
        account = (DepositAccount) buildAbstractAccount(accountElement, account);
        account.setInterestRate(Double.parseDouble(getElementTextContent(accountElement,"interest-rate")));
        return account;
    }



    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }


}

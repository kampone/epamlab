package by.bsu.bankaccount.client;

import by.bsu.bankaccount.account.AbstractAccount;
import by.bsu.bankaccount.account.CurrencyEnum;
import by.bsu.bankaccount.comparator.BalanceAccountComparator;
import by.bsu.bankaccount.exception.LogicException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by note on 18.02.2015.
 */
public class ClientHandler {

    public static double findTheTotalAmount(Client client, CurrencyEnum currencyEnum){
        double result = 0;
        for (AbstractAccount account : client.getAccountList()) {
            if (account.getCurrency() == currencyEnum) {
                result += account.getBalance();
            }
        }
        return result;
    }

    public static double findPositiveAmount(Client client, CurrencyEnum currencyEnum ){
        double result = 0;
        for (AbstractAccount account : client.getAccountList()) {
            if(account.getBalance() > 0 && account.getCurrency() == currencyEnum) {
                result += account.getBalance();
            }
        }
        return result;
    }

    public static double findNegativeAmount(Client client, CurrencyEnum currencyEnum){
        double result = 0;
        for (AbstractAccount account : client.getAccountList()) {
            if(account.getBalance() < 0 && account.getCurrency() == currencyEnum) {
                result += account.getBalance();
            }
        }
        return result;
    }

    public static void sortByBalance(Client client){
        Collections.sort(client.getAccountList(), new BalanceAccountComparator());
    }


}

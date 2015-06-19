package by.bsu.bankaccount.reporter;

import by.bsu.bankaccount.account.AbstractAccount;
import by.bsu.bankaccount.account.CurrencyEnum;
import by.bsu.bankaccount.client.Client;
import by.bsu.bankaccount.client.ClientHandler;

/**
 * Created by note on 19.02.2015.
 */
public class Reporter {

    public static String clientReport(Client client){
        StringBuilder str = new StringBuilder("Client ");
        str.append(client.getFirstName() + " ");
        str.append(client.getSecondName());
        str.append(" has ");
        str.append(client.getAccountList().size());
        str.append(" accounts" + System.lineSeparator());
        for (AbstractAccount account : client.getAccountList()) {
            str.append(account.toString());
        }
        str.append("Total Amount: " + ClientHandler.findTheTotalAmount(client, CurrencyEnum.USD)+ "USD; " +
                ClientHandler.findTheTotalAmount(client,CurrencyEnum.BLR)+"BLR; "+ ClientHandler.findTheTotalAmount(client,CurrencyEnum.EUR)+"EUR; "+
                System.lineSeparator());
        return str.toString();
    }

}

package by.bsu.bankaccount.main;

import by.bsu.bankaccount.client.Client;
import by.bsu.bankaccount.clientbuilder.*;
import by.bsu.bankaccount.reporter.Reporter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.WeakHashMap;

/**
 * Created by note on 16.02.2015.
 */
public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class);
    static{
        PropertyConfigurator.configure("src//property//log4j.properties");
    }
    public static void main(String[] args) {

        ClientBuilderFactory factory = new ClientBuilderFactory();
        AbstractClientBuilder builder = factory.createClientBuilder("dom");
        builder.buildListClient("data//clients.xml");
        for (Client client : builder.getClients()) {
            System.out.println(Reporter.clientReport(client));
        }

    }
}

package by.bsu.callcenter.main;

import by.bsu.callcenter.client.Client;
import by.bsu.callcenter.operator.Operator;
import by.bsu.callcenter.oraganization.Organization;
import org.apache.log4j.PropertyConfigurator;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by note on 15.03.2015.
 */
public class Main {
    static{
        PropertyConfigurator.configure("src//property//log4j.properties");
    }
    public static void main(String[] args) {
        LinkedList<Operator> list = new LinkedList<Operator>();
        list.add(new Operator("1"));
        list.add(new Operator("2"));
        list.add(new Operator("3"));
        list.add(new Operator("4"));
        list.add(new Operator("5"));
        list.add(new Operator("6"));
        list.add(new Operator("7"));
        list.add(new Operator("8"));
        list.add(new Operator("9"));
        list.add(new Operator("10"));
        Organization organization = Organization.getInstance();
        organization.addOperators(list);
        organization.start();
        for (int i = 0; i < 50 ; i++) {
            Client client = new Client(organization);
            client.setName("Client-"+i);
            client.start();
            try {
                Random rand = new Random();
                Thread.sleep(rand.nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

package by.bsu.bankaccount.clientbuilder;

import by.bsu.bankaccount.client.Client;
import by.bsu.bankaccount.exception.TechnicException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by note on 10.03.2015.
 */
public abstract class AbstractClientBuilder {
    protected List<Client> clients;

    public AbstractClientBuilder() {
        clients = new ArrayList<Client>();
    }

    public AbstractClientBuilder(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    protected Integer idFromString(String idString){
        return Integer.parseInt(idString.replaceAll("[^\\d]",""));
    }

    protected GregorianCalendar parseDate(String date) {
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    abstract public void buildListClient(String filename);
}

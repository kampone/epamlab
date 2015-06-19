package by.bsu.bankaccount.client;

import by.bsu.bankaccount.account.AbstractAccount;
import by.bsu.bankaccount.exception.TechnicException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by note on 16.02.2015.
 */
public class Client {
    private int id;
    private String firstName;
    private String secondName;
    private List<AbstractAccount> accountList;
    private static final Logger LOG = Logger.getLogger(Client.class);


    public Client(int id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        accountList = new ArrayList<AbstractAccount>();
        LOG.info("Client is Ready");
    }

    public Client() {

        accountList = new ArrayList<AbstractAccount>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public List<AbstractAccount> getAccountList() {
        return accountList;
    }

    public void addAccount(AbstractAccount account){
        getAccountList().add(account);
    }

    public AbstractAccount getAccount(int i) throws TechnicException {
        try {
            return getAccountList().get(i);
        }catch (Exception e){
            throw new TechnicException("Wrong index of account");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (!accountList.equals(client.accountList)) return false;
        if (!firstName.equals(client.firstName)) return false;
        if (!secondName.equals(client.secondName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + accountList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", accountList=" + accountList +
                '}';
    }


    public void update(){
        for (AbstractAccount account : accountList) {
            account.update();
        }
    }
}

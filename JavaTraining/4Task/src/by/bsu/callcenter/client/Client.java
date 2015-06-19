package by.bsu.callcenter.client;

import by.bsu.callcenter.operator.Operator;
import by.bsu.callcenter.oraganization.Organization;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Created by note on 15.03.2015.
 */
public class Client extends Thread{

    private final static Logger LOG = Logger.getLogger(Client.class);
    private Organization organization;
    private Operator operator;
    private boolean calling;
    private int maxWaitMills;

    public Client(Organization organization) {
        this.organization = organization;
        Random random = new Random();
        maxWaitMills = random.nextInt(2000);
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public boolean isCalling() {
        return calling;
    }

    public void setCalling(boolean calling) {
        this.calling = calling;
    }

    public int getMaxWaitMills() {
        return maxWaitMills;
    }

    private void callToOrganization(){
        calling = true;
        organization.addClient(this);
        System.out.println(this.getName() + " is added to queue");
        LOG.info(this.getName()+ " is added to queue");
    }

    @Override
    public void run() {
        callToOrganization();
        while (calling && operator == null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                LOG.error(e);
            }
        }
        Random random = new Random();
        if(calling) {
            System.out.println("Client " + getName() + " phoned to operator " + operator.getName());
            LOG.info("Client " + getName() + " phoned to operator " + operator.getName());
            try {
                Thread.sleep(random.nextInt(10_000));
                System.out.println(getName() + " put the phone down!");
                LOG.info(getName() + " put the phone down!");
                organization.returnOperator(operator);
            } catch (InterruptedException e) {
                LOG.error(e);
            }
        }else{
            if (random.nextInt(12)%3 == 0) {
                System.out.println("                      " + getName() + " recalling!");
                LOG.info(getName() + " recalling!");
                run();
            }else{
                System.out.println("                      " + getName() + " go away!");
                LOG.info(getName() + " go away!");
            }
        }
    }
}

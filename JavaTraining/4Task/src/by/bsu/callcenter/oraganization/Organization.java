package by.bsu.callcenter.oraganization;

import by.bsu.callcenter.client.Client;
import by.bsu.callcenter.operator.Operator;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by note on 15.03.2015.
 */
public class Organization extends Thread{
    private final static Logger LOG = Logger.getLogger(Organization.class);
    private final int CALLCENTER_SIZE = 10;
    private Queue<Client> clients = new LinkedList<>();
    private Queue<Operator> freeOperators = new LinkedList<>();
    private final Semaphore semaphore = new Semaphore(CALLCENTER_SIZE, true);
    private static Organization organization;
    private static Lock lock = new ReentrantLock();

    private Organization() {
    }

    @Override
    public void run() {
        System.out.println("Organization is working");
        while (true) {
            if (!clients.isEmpty()) {
                try {
                    Client client = clients.poll();
                    if(semaphore.tryAcquire(client.getMaxWaitMills(), TimeUnit.MILLISECONDS)){
                        Operator operator = freeOperators.poll();
                        client.setOperator(operator);
                    }else{
                        client.setCalling(false);

                    }
                } catch (InterruptedException e) {
                    LOG.error(e);
                }

            }else{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    LOG.error(e);
                }
            }

        }
    }


    public static Organization getInstance() {
        lock.lock();
        if (organization == null) {
            organization = new Organization();
        }
        lock.unlock();
        return organization;
    }

    public void addOperators(Queue<Operator> operators) {

        organization.freeOperators.addAll(operators);
    }



    public boolean addClient(Client client){
        return clients.add(client);
    }

    public boolean returnOperator(Operator operator) {
        System.out.println("Operator " + operator.getName() + " is released");
        LOG.info("Operator " + operator.getName() + " is released");
        organization.semaphore.release();
        return organization.freeOperators.add(operator);

    }

}

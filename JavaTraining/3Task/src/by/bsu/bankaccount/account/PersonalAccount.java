package by.bsu.bankaccount.account;

import by.bsu.bankaccount.exception.LogicException;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by note on 15.02.2015.
 */
public class PersonalAccount extends AbstractAccount {


    public PersonalAccount() {
    }

    public void cashIn(double cash){
        setBalance(getBalance() + cash);
    }

    public void cashOut(double cash){
        setBalance(getBalance() - cash);
    }

    @Override
    public void update() {
        setUpdateDate(new GregorianCalendar());
    }

    @Override
    public String toString() {
        return super.toString()+System.lineSeparator();
    }

}

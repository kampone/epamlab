package by.bsu.bankaccount.account;


import by.bsu.bankaccount.exception.LogicException;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by note on 15.02.2015.
 */
public class CreditAccount extends AbstractAccount {
    private static final long SECONDS_IN_MONTH = (long)30*24*60*60*1000;
    private double lendingRate;
    private int month;


    public CreditAccount() {
    }

    public double monthlyPayment() {
        return (getBalance() + getBalance() * lendingRate*0.01)/month;
    }


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getLendingRate() {
        return lendingRate;
    }

    public void setLendingRate(double lendingRate) throws LogicException {
        if(lendingRate > 0 && lendingRate < 80) {
            this.lendingRate = lendingRate;
        }else{
            throw new LogicException("");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditAccount)) return false;
        if (!super.equals(o)) return false;

        CreditAccount that = (CreditAccount) o;

        if (Double.compare(that.lendingRate, lendingRate) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(lendingRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                "lendingRate=" + lendingRate +
                 " month=" + month +System.lineSeparator();
    }

    @Override
    public void update() {
        long month = (new GregorianCalendar().getTimeInMillis() - this.getUpdateDate().getTimeInMillis())/SECONDS_IN_MONTH;
        while (month != 0 && getMonth() != 0) {
            setBalance(getBalance() - monthlyPayment());
            --month;
            setMonth(getMonth()-1);
        }
        System.out.println("hello!");
        setUpdateDate(new GregorianCalendar());
    }
}

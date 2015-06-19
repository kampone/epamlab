package by.bsu.bankaccount.account;

import by.bsu.bankaccount.exception.LogicException;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by note on 15.02.2015.
 */
public class DepositAccount extends AbstractAccount {
  private double interestRate;
    private static final long SECONDS_IN_MONTH = (long)30*24*60*60*1000;


    public DepositAccount() {

    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) throws LogicException {
        if(interestRate > 0 && interestRate < 80) {
            this.interestRate = interestRate;
        }else{
            throw new LogicException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepositAccount)) return false;
        if (!super.equals(o)) return false;

        DepositAccount that = (DepositAccount) o;

        if (Double.compare(that.interestRate, interestRate) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(interestRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                "interestRate=" + interestRate +
                System.lineSeparator();
    }

    @Override
    public void update() {
        long month = (new GregorianCalendar().getTimeInMillis() - this.getUpdateDate().getTimeInMillis())/SECONDS_IN_MONTH;
        for(int i=0; i < month; ++i){
           setBalance(getBalance() + (getBalance() * interestRate * 0.01)/12);
        }
        this.setUpdateDate(new GregorianCalendar());
    }
}

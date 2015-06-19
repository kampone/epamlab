package by.bsu.bankaccount.account;


import by.bsu.bankaccount.exception.LogicException;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by note on 15.02.2015.
 */
public abstract class AbstractAccount {
    private int id;
    private boolean isBlocked;
    private GregorianCalendar updateDate;
    private double balance;
    private CurrencyEnum currency;

    private static final Logger LOG = Logger.getLogger(AbstractAccount.class);



    public AbstractAccount() {
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public abstract void update();

    public int getId() {
        return id;
    }

    public void setId(int id) throws LogicException{
        if (id > 0) {
            this.id = id;
        }else throw new LogicException();
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public void setUpdateDate(GregorianCalendar updateDate) {
        this.updateDate = updateDate;
    }

    public GregorianCalendar getUpdateDate() {
        return updateDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAccount)) return false;

        AbstractAccount account = (AbstractAccount) o;

        if (Double.compare(account.balance, balance) != 0) return false;
        if (id != account.id) return false;
        if (isBlocked != account.isBlocked) return false;
        if (updateDate != null ? !updateDate.equals(account.updateDate) : account.updateDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }



    @Override
    public String toString() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = timeFormat.format(new Date(updateDate.getTimeInMillis()));
        return this.getClass().getSimpleName() +
                ": id=" + id +
                ", isBlocked=" + isBlocked +
                ", updateDate=" + time +
                ", balance=" + balance + " " +  currency +
                " ";
    }


}


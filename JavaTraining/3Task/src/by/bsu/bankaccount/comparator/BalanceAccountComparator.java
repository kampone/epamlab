package by.bsu.bankaccount.comparator;

import by.bsu.bankaccount.account.AbstractAccount;

import java.util.Comparator;

/**
 * Created by note on 19.02.2015.
 */
public class BalanceAccountComparator implements Comparator<AbstractAccount> {
    @Override
    public int compare(AbstractAccount o1, AbstractAccount o2) {
        return Double.compare(o1.getBalance(),o2.getBalance());
    }
}

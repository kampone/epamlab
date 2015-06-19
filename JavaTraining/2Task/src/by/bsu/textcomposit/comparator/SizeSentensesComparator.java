package by.bsu.textcomposit.comparator;

import by.bsu.textcomposit.texthandler.TextHandler;
import by.bsu.textcomposit.textpart.*;
import org.apache.log4j.Logger;

import java.util.Comparator;

/**
 * Created by note on 01.03.2015.
 */
public class SizeSentensesComparator implements Comparator<Component> {
    private static final Logger log = Logger.getLogger(SizeSentensesComparator.class);
    @Override
    public int compare(Component o1, Component o2) {
        return TextHandler.numberOfWords(o1)- TextHandler.numberOfWords(o2);
    }



}


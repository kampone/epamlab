package by.bsu.textcomposit.reporter;

import by.bsu.textcomposit.texthandler.TextHandler;
import by.bsu.textcomposit.textpart.Component;
import by.bsu.textcomposit.textpart.TextComposite;

import java.util.List;

/**
 * Created by note on 03.03.2015.
 */
public class Reporter {
    public static String reportSortedListOfSentences(TextComposite text){
        List<Component> sorted = TextHandler.findSortedSentenceList(text);
        StringBuilder str = new StringBuilder();
        str.append("Sentences from this text was sorted!" + System.lineSeparator()+System.lineSeparator());
        for (Component component : sorted) {
            str.append(" Number of words:" + TextHandler.numberOfWords(component) +System.lineSeparator()+ component.toString());
            str.append(System.lineSeparator());
            str.append(System.lineSeparator());
        }
        return str.toString();
    }

    public static String reportNonrecuringWord(TextComposite text){
        StringBuilder str = new StringBuilder();
        str.append("Nonrecuring word from first sentence is: " +
                TextHandler.findNonrecuringWord(text).toString().toUpperCase() +
                System.lineSeparator());
        return str.toString();
    }

    public static String generalReport(TextComposite text){
        return reportSortedListOfSentences(text) + reportNonrecuringWord(text);
    }
}

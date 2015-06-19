package by.bsu.textcomposit.main;

import by.bsu.textcomposit.reporter.Reporter;
import by.bsu.textcomposit.texthandler.TextHandler;
import by.bsu.textcomposit.textpart.Component;
import by.bsu.textcomposit.textpart.TextComposite;
import by.bsu.textcomposit.typepart.TypePart;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

/**
 * Created by note on 25.02.2015.
 */
public class Main {
    public static final Logger log = Logger.getLogger(Main.class);
    static{
        PropertyConfigurator.configure("src//property//log4j.properties");
    }
    public static void main(String[] args) {
        TextComposite textComposite = null;
        try {
            textComposite = TextHandler.textCreator(TextHandler.parseFile("text//text.txt"));


System.out.println(textComposite.toString());
            System.out.println(Reporter.generalReport(textComposite));
        } catch (IOException e) {
            log.error(e);
        }
    }
}

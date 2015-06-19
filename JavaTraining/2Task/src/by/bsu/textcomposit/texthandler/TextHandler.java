package by.bsu.textcomposit.texthandler;

import by.bsu.textcomposit.comparator.SizeSentensesComparator;
import by.bsu.textcomposit.textpart.*;
import by.bsu.textcomposit.typepart.TypePart;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by note on 25.02.2015.
 */
public class TextHandler {
    private static final String PARAGRAPH = "\\s{4,}[A-Za-z,.():'\\-\\d\\s\\n]+(\\.|!|\\?)";
    private static final String LISTING = "//:[^\\t]+///:~";
    private static final String PARAGRAPH_OR_LISTING = "((\\s{4,}[A-Za-z,.():'\\-\\d\\s]+(\\.|!|\\?)))|(//:[^\\t]+///:~)";
    private static final String SENTENCE = "([^(\\.|!|\\?)]+)(\\.|!|\\?)";
    private static final String WORD = "[A-Za-z'\\d]+";
    private static final String SYMBOL = "[.,!?:;]";
    private static final String WORD_OR_SYMBOL = "[A-Za-z\\d]+|[.,!?:;]";
    private static final Logger log = Logger.getLogger(TextHandler.class);

    public static String parseFile(String url) throws IOException {
        StringBuilder str = new StringBuilder();
        int b;
        FileReader fileReader = new FileReader(new File(url));
            while ((b = fileReader.read()) != -1){
                str.append((char) b);
            }
        return str.toString();
    }

    public static TextComposite textCreator(String str) {
        TextComposite text = new TextComposite();

        String paragraphAndListing = null;
        String paragraph = null;
        String sentence = null;
        String wordAndSymbol = null;

        Pattern patternParagraphAndListing = Pattern.compile(PARAGRAPH_OR_LISTING);
        Pattern patternParagraph = Pattern.compile(PARAGRAPH);
        Pattern patternListing = Pattern.compile(LISTING);
        Pattern patternSentence = Pattern.compile(SENTENCE);
        Pattern patternWordAndSymbol = Pattern.compile(WORD_OR_SYMBOL);
        Pattern patternWord = Pattern.compile(WORD);
        Pattern patternSymbol = Pattern.compile(SYMBOL);

        Matcher matcherListing;
        Matcher matcherParagraph;
        Matcher matcherSentence;
        Matcher matcherWordAndSymbol;
        Matcher matcherWord;
        Matcher matcherSymbol;

        Matcher matcherParagraphAndListing = patternParagraphAndListing.matcher(str);

        while (matcherParagraphAndListing.find()) {
            paragraphAndListing = matcherParagraphAndListing.group();
            matcherParagraph = patternParagraph.matcher(paragraphAndListing);
            matcherListing = patternListing.matcher(paragraphAndListing);
            if (matcherListing.find()) {
                text.addComponent(new Leaf(matcherListing.group(), TypePart.LISTING));
            }else {
                while (matcherParagraph.find()) {
                    paragraph = matcherParagraph.group();
                    matcherSentence = patternSentence.matcher(paragraph);
                    Composite compositeParagraph = new Composite(TypePart.PARAGRAPH);
                    while (matcherSentence.find()) {
                        sentence = matcherSentence.group();
                        matcherWordAndSymbol = patternWordAndSymbol.matcher(sentence);
                        Composite compositeSentence = new Composite(TypePart.SENTENCE);
                        while (matcherWordAndSymbol.find()) {
                            wordAndSymbol = matcherWordAndSymbol.group();
                            matcherWord = patternWord.matcher(wordAndSymbol);
                            matcherSymbol = patternSymbol.matcher(wordAndSymbol);
                            if (matcherWord.find()) {
                                compositeSentence.addComponent(new Leaf(matcherWord.group(),TypePart.WORD));
                            }else if(matcherSymbol.find()){
                                compositeSentence.addComponent(new Leaf(matcherSymbol.group(),TypePart.SYMBOL));
                            }
                        }
                        compositeParagraph.addComponent(compositeSentence);
                    }
                    text.addComponent(compositeParagraph);
                }
            }
        }
        return text;
    }

    public static List findSortedSentenceList(TextComposite textComposite){
        List<Component> list = new ArrayList<Component>();
        for (Component component : textComposite.getComponents()) {
            if(component.getType() == TypePart.PARAGRAPH) {
                for (Component component1 : component.getComponents()) {
                    list.add(component1);
                }
            }
        }
        Collections.sort(list, new SizeSentensesComparator());
        return list;
    }

    public static Component findNonrecuringWord(TextComposite text){
        List<Component> wordsFromFirstSentence = new ArrayList<Component>();
        List<Component> otherWords = new ArrayList<Component>();

        for (Component component : findParagraph(text, 0).getComponents().get(0).getComponents()) {
            if (component.getType() == TypePart.WORD) {
                wordsFromFirstSentence.add(component);
            }
        }
        for (Component component : text.getComponents()) {
            if(component.getType() == TypePart.PARAGRAPH) {
                for (Component component1 : component.getComponents()) {
                    for (Component component2 : component1.getComponents()) {
                        if(component2.getType() == TypePart.WORD) {
                            otherWords.add(component2);
                            if (otherWords.equals(wordsFromFirstSentence)) {
                                otherWords.clear();
                            }

                        }
                    }

                }
            }

        }
        wordsFromFirstSentence.removeAll(otherWords);


        log.info(wordsFromFirstSentence);
        if(wordsFromFirstSentence.get(0) == null){
            log.info("No Such Word!");
        }
        return wordsFromFirstSentence.get(0);
    }


    public static int numberOfWords(Component component){
        int words = 0;
        if(component.getType() != TypePart.SENTENCE){
            log.error("Illegal component");
        }
        for (Component component1 : component.getComponents()) {
            if(component1.getType() == TypePart.WORD){
                ++words;
            }
        }
        return words;
    }
    public static Component findParagraph(TextComposite text, int i){
        List<Component> list = new ArrayList<Component>();
        for (Component component : text.getComponents()) {
            if (component.getType() == TypePart.PARAGRAPH){
                list.add(component);
            }
        }
        if((i+1) >= list.size()) {
            log.error("No such paragraph");
        }
        return list.get(i);
    }

}

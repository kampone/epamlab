package by.bsu.callcenter.operator;

import org.apache.log4j.Logger;

/**
 * Created by note on 15.03.2015.
 */
    public class Operator {
    private final static Logger LOG = Logger.getLogger(Operator.class);
    private String name;
    public Operator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}

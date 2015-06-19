package by.bsu.bankaccount.clientbuilder;

/**
 * Created by note on 10.03.2015.
 */
public enum ClientEnum {
    CLIENTS("clients"),
    CLIENT("client"),
    ID("id"),
    FIRSTNAME("firstname"),
    LASTNAME("lastname"),
    PERSONALACCOUNT("personalaccount"),
    CREDITACCOUNT("creditaccount"),
    DEPOSITACCOUNT("depositaccount"),
    CURRENCY("currency"),
    ISBLOCKED("isblocked"),
    UPDATEDATE("updatedate"),
    BALANCE("balance"),
    INTERESTRATE("interestrate"),
    LENDINGRATE("lendingrate"),
    MONTH("month");

    private String value;
    ClientEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

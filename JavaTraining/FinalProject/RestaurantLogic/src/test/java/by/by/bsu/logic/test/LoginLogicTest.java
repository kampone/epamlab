package by.bsu.logic.test;
import by.bsu.logic.login.LoginChecker;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by note on 07.05.2015.
 */
public class LoginLogicTest {
    @Test
    public void checkFormatWrongTest(){
        String login = "adm";
        boolean actual = LoginChecker.checkFormat(login);
        Assert.assertEquals(false, actual);
    }
    @Test
    public void checkFormatRightTest(){
        String login = "admin";
        boolean actual = LoginChecker.checkFormat(login);
        Assert.assertEquals(true, actual);
    }
}

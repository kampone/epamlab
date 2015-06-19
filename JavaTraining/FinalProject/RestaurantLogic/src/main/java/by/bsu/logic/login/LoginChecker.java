package by.bsu.logic.login;

import by.bsu.logic.exception.LogicException;
import by.bsu.logic.entity.UserLogic;
import by.bsu.entity.user.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginChecker {
    private static final Logger LOG = Logger.getLogger(LoginChecker.class);
    private static final String LOGIN_AND_PASS_REGEX = "\\S{4,10}";

    /**
     * @param enterLogin Login
     * @param enterPass Password
     * @return true if login and pass correct
     * @throws LogicException if some Connection or DAO problems
     */
    public static boolean checkLogin(String enterLogin, String enterPass) throws LogicException{
        User user = UserLogic.takeUserByLogin(enterLogin);
        if (user != null) {
            return DigestUtils.md5Hex(enterPass).equals(user.getPassword());
        }else{
            return false;
        }
    }

    /**
     * @param str Login or Password
     * @return true if str has right format
     */
    public static boolean checkFormat(String str){
        Pattern format = Pattern.compile(LOGIN_AND_PASS_REGEX);
        Matcher matcher = format.matcher(str);
        return matcher.matches();
    }
}

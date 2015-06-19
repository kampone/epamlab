package by.bsu.service.action;

import by.bsu.service.command.common.EmptyCommand;
import by.bsu.service.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Used to define what command should be execute
 * @see CommandEnum
 * @see ActionCommand
 */
public class ActionFactory {
    private static final Logger LOG = Logger.getLogger(ActionFactory.class);

    /**
     * Define what command should be execute
     * @param request HttpServletRequest
     * @return class implements ActionCommand
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = null;
        String action = request.getParameter("command");
        HttpSession session = request.getSession();
        if (action == null || action.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            LOG.error(e);
            String message = MessageManager.getProperty("message.wrongaction", session);
            request.setAttribute("wrongAction", action + message);
        }  return current;

    }


}

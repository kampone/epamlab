package by.bsu.service.action;

import by.bsu.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface that implement each of Command in ServiceLayer
 * Functional interface
 */
public interface ActionCommand  {

    /**
     * @param request HttpServletRequest
     * @return page to generate for user
     */
    String execute(HttpServletRequest request);

    /**
     * Helps to processing exceptions in Service Layer
     * @param request HttpServletRequest
     * @param e Exception
     * @param logger Logger Log4j
     * @return address of error page
     */
    default String exeptionHelp(HttpServletRequest request, Exception e, Logger logger){
        logger.error(e);
        request.setAttribute("errorMessage", e);
        return ConfigurationManager.getProperty("path.page.error");
    }
}

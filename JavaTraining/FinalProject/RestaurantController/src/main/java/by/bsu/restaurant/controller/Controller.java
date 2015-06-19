package by.bsu.restaurant.controller;

import by.bsu.connectionpool.exception.ConnectionPoolException;
import by.bsu.connectionpool.pool.ConnectionPool;
import by.bsu.service.action.ActionCommand;
import by.bsu.service.action.ActionFactory;
import by.bsu.service.manager.ConfigurationManager;
import by.bsu.service.manager.MessageManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller listens to and processes the requests
 */
public class Controller extends HttpServlet{
    private static final Logger LOG = Logger.getLogger(Controller.class);
    @Override
    public void init() throws ServletException {
        PropertyConfigurator.configure("property/log4j.properties");
        ConnectionPool.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().closeConnections();
        } catch (ConnectionPoolException e) {
            LOG.error(e);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = null;
        ActionFactory client = new ActionFactory();//?
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            page = ConfigurationManager.getProperty("path.page.index");
            String message = MessageManager.getProperty("message.nullpage", session);
            request.getSession().setAttribute("nullPage", message);
            response.sendRedirect(request.getContextPath() + page);
        }

    }
}

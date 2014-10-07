package servlets.commands;

import beans.User;
import businesslogic.UserManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Damien on 05.10.2014.
 */
public class Logout implements CommandExecutor {
    private static final Logger log = Logger.getLogger(Logout.class);
    private final UserManager userManager = UserManager.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            log.error("Can't get Writer from response", e);
            return;
        }
        try {
            String userName = request.getSession().getAttribute("login").toString();
            User user = new User(userName);
            userManager.removeUser(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            out.println("<font color=red>You have been deleted from Chat.</font>");
            try {
                dispatcher.include(request, response);
            } catch (ServletException e) {
                log.error("Redirection error occurred", e);
            } catch (IOException e) {
                log.error("IO Error while redirection", e);
            }
        } finally {
            out.close();
        }
    }
}

package servlets.commands;

import beans.User;
import businesslogic.UserManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Damien on 05.10.2014.
 */
public class Login implements CommandExecutor {
    private static final Logger log = Logger.getLogger(Login.class);
    private final UserManager userManager = UserManager.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("login");
        // check for valid data
        if(!userName.isEmpty()) {
            User user = new User(userName);
            if (userManager.addUser(user)) {
                log.info("The new user was added: " + userName);
                HttpSession session = request.getSession();
                session.setAttribute("login", userName);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/chat.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException e) {
                    log.error("Redirection error occurred", e);
                } catch (IOException e) {
                    log.error("IO Error while redirection", e);
                }
            } else {
                PrintWriter out;
                try {
                    out = response.getWriter();
                } catch (IOException e) {
                    log.error("Can't get Writer from response", e);
                    return;
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                // remove to front-end
                out.println("<font color=red>Current user name is already used.</font>");
                try {
                    dispatcher.include(request, response);
                } catch (ServletException e) {
                    log.error("Redirection error occurred", e);
                } catch (IOException e) {
                    log.error("IO Error while redirection", e);
                }
            }
        }
    }

}

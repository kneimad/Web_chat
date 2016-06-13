package servlets.commands;

import beans.User;
import businesslogic.UserManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Damien on 05.10.2014.
 */
public class GetUsers implements CommandExecutor {
    private static final Logger log = Logger.getLogger(GetUsers.class);
    private UserManager userManager = UserManager.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder stringBuilder = new StringBuilder();
        // XML or JSON
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuilder.append("<users>");
        for (User user : userManager.getUsers()) {
            stringBuilder.append("<user>").append(user.getName()). append("</user>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        stringBuilder.append("</users>");
        try {
            response.getWriter().write(stringBuilder.toString());
        } catch (IOException e) {
            log.error("Can't get Writer from response", e);
        }
    }
}

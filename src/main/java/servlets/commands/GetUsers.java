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
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuffer.append("<users>");
        for (User user : userManager.getUsers()) {
            stringBuffer.append("<user>" + user.getName() + "</user>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        stringBuffer.append("</users>");
        try {
            response.getWriter().write(stringBuffer.toString());
        } catch (IOException e) {
            log.error("Can't get Writer from response", e);
        }
    }
}

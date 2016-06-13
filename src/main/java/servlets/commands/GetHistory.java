package servlets.commands;

import beans.User;
import datastorage.MessageHistory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Damien on 05.10.2014.
 */
public class GetHistory implements CommandExecutor {
    private static final Logger log = Logger.getLogger(GetHistory.class);
    private MessageHistory messageHistory = MessageHistory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder stringBuilder = new StringBuilder();
        String userName = request.getSession().getAttribute("login").toString();
        // check to valid userName
        if(!userName.isEmpty()) {
            User user = new User(userName);
            String message = messageHistory.extractUserHistory(user);
            // XML or JSON
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            if (message != null && !message.isEmpty()) {
                log.info("\nMessage: " + message + " From user: " + user.getName());
                stringBuilder.append(message);
                try {
                    response.getWriter().write(stringBuilder.toString());
                } catch (IOException e) {
                    log.error("Can't get Writer from response", e);
                }
            } else {
                log.info("\nMessage: NOT RETRIEVED" + user.getName());
                stringBuilder.append("\nMessage: NOT RETRIEVED");
            }
        }
    }
}

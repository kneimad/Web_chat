package com.mycompany.servlets.commands;

import com.mycompany.beans.User;
import com.mycompany.datastorage.MessageHistory;
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
        String userName = request.getSession().getAttribute("login").toString();
        User user = new User(userName);
        String message = messageHistory.extractUserHistory(user);
        if (message != null && !message.isEmpty()) {
            log.info("\nMessage: " + message + " From user: " + user.getName());
            StringBuffer stringBuffer = new StringBuffer();

            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            stringBuffer.append(message);
            log.info("\n" + stringBuffer.toString());
            try {
                response.getWriter().write(stringBuffer.toString());
            } catch (IOException e) {
                log.error("Can't get Writer from response", e);
            }
        }
    }
}

package com.mycompany.servlets.commands;

import com.mycompany.beans.User;
import com.mycompany.beans.UserMessage;
import com.mycompany.datastorage.MessageHistory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Damien on 05.10.2014.
 */
public class SendMessage implements CommandExecutor {
    private static final Logger log = Logger.getLogger(SendMessage.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    private MessageHistory messageHistory = MessageHistory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getSession().getAttribute("login").toString();
        User user = new User(userName);
        String message = request.getParameter("message");
        log.info("From Server --> the message was sent: " + message);
        UserMessage userMessage = new UserMessage(user, message);
        messageHistory.addMessage(userMessage);
        log.info("[" + dateFormat.format(userMessage.getTime()) + "] " + userMessage.getUser().getName() + " : " + userMessage.getMessage());

    }
}

package com.mycompany.servlets;

import com.mycompany.beans.User;
import com.mycompany.beans.UserMessage;
import com.mycompany.businesslogic.UserManager;
import com.mycompany.datastorage.MessageHistory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Damien on 02.10.2014.
 */
public class ChatServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ChatServlet.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    private UserManager userManager = UserManager.getInstance();
    private MessageHistory messageHistory = MessageHistory.getInstance();
    private User user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userName = request.getSession().getAttribute("login").toString();
        user = new User(userName);
        log.info("\nAction: " + action);
//        System.out.print("\nUser: " + userName);

        if (action.equals("getUsers")) {
            Set<User> users = new HashSet<User>(userManager.getUsers().keySet());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            stringBuffer.append("<users>");
            for (User user : users) {
                stringBuffer.append("<user>" + user.getName() + "</user>");
            }

            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            stringBuffer.append("</users>");
            //System.out.println(stringBuffer.toString());
            response.getWriter().write(stringBuffer.toString());
        }

        if (action.equals("sendMessage")) {
            log.info("\nFrom Server --> the message was sent: ");
            String message = request.getParameter("message");
            log.info(message);
            UserMessage userMessage = new UserMessage(user, message);
            messageHistory.addMessage(userMessage);
            //userMessage.setTime(new Date());
            log.info("\n[" + dateFormat.format(userMessage.getTime()) + "] " + userMessage.getUser().getName() + " : " + userMessage.getMessage());
        }

        if (action.equals("getHistory")) {
            String message = messageHistory.extractUserHistory(user);
            log.info("\nMessage: " + message + " From user: " + user.getName());
            StringBuffer stringBuffer = new StringBuffer();

            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            stringBuffer.append(message);
            log.info("\n" + stringBuffer.toString());
            response.getWriter().write(stringBuffer.toString());
        }
    }

}

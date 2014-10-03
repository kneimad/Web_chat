package com.mycompany.servlets;

import com.mycompany.beans.User;
import com.mycompany.beans.UserMessage;
import com.mycompany.businesslogic.UserManager;
import com.mycompany.datastorage.MessageHistory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Damien on 02.10.2014.
 */
public class ChatServlet extends HttpServlet {

    private UserManager userManager = UserManager.getInstance();
    private MessageHistory messageHistory = MessageHistory.getInstance();
    private User user;

    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userName = request.getSession().getAttribute("login").toString();
        user = new User(userName);
        System.out.print("\nAction: " + action);
        //System.out.println("User: " + userName);

        if (action.equals("getUsers")) {
            HashSet<User> users = new HashSet<User>(userManager.getUsers());
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
            System.out.print("\nFrom Server --> the message was sent: ");
            String message = request.getParameter("message");
            System.out.print(message);
            UserMessage userMessage = new UserMessage(user, message);
            messageHistory.addMessage(userMessage);
            //userMessage.setTime(new Date());
            System.out.print("\n[" + dateFormat.format(userMessage.getTime()) + "] " + userMessage.getUser().getName() + " : " + userMessage.getMessage());
        }

        if (action.equals("getHistory")) {
            String message = messageHistory.extractUserHistory(user);
            System.out.print("\nMessage: " + message + " From user: " + user.getName());
            StringBuffer stringBuffer = new StringBuffer();

            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            stringBuffer.append(message);
            System.out.print("\n" + stringBuffer.toString());
            response.getWriter().write(stringBuffer.toString());
        }
    }

}

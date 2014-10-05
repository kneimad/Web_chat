package com.mycompany.servlets;

import com.mycompany.servlets.commands.ChatAction;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Damien on 02.10.2014.
 */
public class ChatServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ChatServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        log.info("\nAction: " + action);
        ChatAction chatAction = ChatAction.getByCommand(action);
        if (chatAction != null) {
            chatAction.execute(request, response);
        } else {
            log.warn("No executor for action: " + action);
        }
    }
}

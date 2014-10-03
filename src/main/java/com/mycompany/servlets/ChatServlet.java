package com.mycompany.servlets;

import com.mycompany.beans.User;
import com.mycompany.businesslogic.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Damien on 02.10.2014.
 */
public class ChatServlet extends HttpServlet {

    private UserManager userManager = UserManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action.equals("getUsers")) {

            HashSet<User> users = new HashSet<User>(userManager.getUsers());
            StringBuffer stringBuffer = new StringBuffer();
            for (User user : users) {
                //stringBuffer.append("<user>" + user.getName() + "</user>");
                stringBuffer.append("<user>" + user.getName() + "</user>");
            }

            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            System.out.println(stringBuffer.toString());
            //response.getWriter().write("<users>" + stringBuffer.toString() + "</users>");
            response.getWriter().write(stringBuffer.toString());
        }
    }

}

package com.mycompany.servlets;

import com.mycompany.beans.User;
import com.mycompany.businesslogic.UserManager;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    private UserManager userManager;

    @Override
    public void init() throws ServletException {
        super.init();
        userManager = UserManager.getInstance();
        
    }
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        //get request parameters for userID and password
         String userName = request.getParameter("login");
         User user = new User(userName);

        //logging example
        //log("User="+user+"::password="+pwd);
         
        if(userManager.isUserNameUsed(user)){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            out.println("<font color=red>Current user name is already used.</font>");
            dispatcher.include(request, response);            
        }
        else{
            //--------- session 
            /*HttpSession session = request.getSession();
            session.setAttribute("login", user);
            response.sendRedirect("name.jsp");      */
            
            //--------- request
            request.setAttribute("login", user.getName());
            userManager.addUser(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/chat.jsp");
            dispatcher.forward(request, response);
        }
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for users login";
    }// </editor-fold>
}
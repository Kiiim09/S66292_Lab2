package com.mycompany.servletdatasharing;

import java.io.IOException;
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.annotation.*;

public class LoginServlet extends HttpServlet {

    HashMap <String, String> users = new HashMap();
    
    @Override
    public void init() throws ServletException {
        super.init();
        users.put("Ali","1234");
        users.put("Ahmad","4567");
        users.put("Muthu","8910");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
       String username = request.getParameter("txtUsername");
       String password = request.getParameter("txtPassword");
       
       if (!username.equals("") && !password.equals("")
                && users.get(username).equals(password)) {
                request.setAttribute("userid", username);
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/AccountServlet");
                rd.forward(request, response);
        } else {
           RequestDispatcher rd = request.getRequestDispatcher("/login.html");
           rd.forward(request, response);
       }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

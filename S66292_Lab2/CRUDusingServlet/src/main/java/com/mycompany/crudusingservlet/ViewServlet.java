package com.mycompany.crudusingservlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<a href='index.html'>Add New User</a>");
        out.println("<h1>User List</h1>");
        
        List<User> list = UserDao.getAllUsers();
        
        out.print("<table border='1' width='100%'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Role</th><th>Edit</th><th>Delete</th>");
        for (User e : list) {
            out.print("<tr><td>" + e.getId() + "</td><td>" + e.getUsername() + "</td><td>" 
                    + e.getPassword() + "</td><td>" + e.getRole() + "</td><td><a href='EditServlet?id="
                    + e.getId() + "'>Edit</a></td><td><a href='DeleteServlet?id=" 
                    + e.getId() + "'>Delete</a></td></tr>"
            );
        }
        out.print("</table>");
        
        out.close();
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

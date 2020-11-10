package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>First name:" + firstName + "</h2>");
            writer.println("<h2>Last name:" + lastName + "</h2>");
            writer.println("<h2>Email:" + email + "</h2>");
            writer.println("<h2>Password:" + password + "</h2>");
        } finally {
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = "/login.html";
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}

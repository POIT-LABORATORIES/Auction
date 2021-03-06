package servlets;

import app.models.User;
import app.service.ServiceFactory;
import app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        PrintWriter writer = response.getWriter();
        try{
            String firstName = request.getParameter("first-name");
            String email = request.getParameter("email");
            String password = request.getParameter("pass");

            User user = new User(-1, firstName, email, password);

            switch (request.getParameter("action")){
                case "authenticate":
                    user = userService.authorization(user);
                    break;
                case "register":
                    userService.registration(user);
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    break;
                case "logout":
                    session.invalidate();
                    response.sendRedirect(request.getContextPath() + "/main");
                    break;
            }

            // Synchronized access to session object.
            final Object lock = session.getId().intern();
            synchronized (lock){
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(-1);
            }

            response.sendRedirect(request.getContextPath() + "/main");
        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {
            writer.close();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").
                forward(request, response);
    }
}

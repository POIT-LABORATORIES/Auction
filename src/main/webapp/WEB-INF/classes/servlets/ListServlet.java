package servlets;

import app.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User userList = new User("Zek", "Zek@mail.ru", "zek2020");
        request.setAttribute("users", userList);
        getServletContext().getRequestDispatcher("/UserList.jsp").forward(request, response);
    }
}

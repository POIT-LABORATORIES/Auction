package myapp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller extends HttpServlet {
    public Controller(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        /*
        // Получение атрибута "get" из строки запроса.
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        // получаем параметр id
        String id = request.getParameter("id");

        try {
            writer.println("<h2>Id:" + id + "</h2>");
        } finally {
            writer.close();
        }
        */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        requestDispatcher.forward(request, response);
    }
}

package servlets;

import app.dao.UserDAO;
import app.dao.UserDAOImpl;
import app.models.User;
import app.service.UserService;
import app.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String firstName = request.getParameter("first-name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        //HttpSession session = request.getSession();
        //Cookie[] cookie = request.getCookies();

        /*
        String insertQuery = "INSERT INTO `users` " +
        "(`user_id`, `user_name`, `email`, `password`, `reg_date`, `last_reg_date`) " +
        "VALUES (NULL, 'Kirill', 'Foolman.ru@yandex.ru', 'Kirka123', '2020-11-24', '2020-11-24');";
        */

        UserService userService = new UserServiceImpl();
        PrintWriter writer = response.getWriter();
        try{
            /*
            String url = "jdbc:mysql://localhost/auction_db?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "Rookie";
            String bdPassword = "grasp0189rob";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            */
            // try (Connection conn = DriverManager.getConnection(url, username, bdPassword))

            // Получить список всех пользователей, добавить в атрибут "users" response.
            List<User> userList = userService.getAllUsers();
            request.setAttribute("users", userList);


            /*
            // getting Statement object to execute query
            Statement stmt = conn.createStatement();

            String selectQuery = "select * from users";
            // executing SELECT query
            ResultSet rs = stmt.executeQuery(selectQuery);

            while (rs.next()) {
                int count = rs.getInt(1);
                //writer.println("Total number of users in the table : " + count);
            }
            */

            getServletContext().getRequestDispatcher("/UserList.jsp").forward(request, response);
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
        getServletContext().getRequestDispatcher("/login.html").forward(request, response);
    }
}

package servlets;

import app.models.User;
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

        PrintWriter writer = response.getWriter();
        try{
            String url = "jdbc:mysql://localhost/auction_db?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String bdPassword = "webgod2020";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, bdPassword)){

                writer.println("Connection is established!");
                // Получить список всех пользователей, добавить в атрибут "users" response.
                UserServiceImpl userService = new UserServiceImpl();
                List<User> userList = userService.getAllUsers();
                //
                String userName = request.getParameter("first-name");
                String userEmail = request.getParameter("email");
                String userPassword = request.getParameter("pass");
                User user = new User(userName, userEmail, userPassword);
                userList.add(user);
                User additUser = new User("AdditUser", "Luspl@ya.ru", "1234");
                userList.add(additUser);
                //
                request.setAttribute("users", userList);


                // getting Statement object to execute query
                Statement stmt = conn.createStatement();

                String selectQuery = "select * from users";
                // executing SELECT query
                ResultSet rs = stmt.executeQuery(selectQuery);

                while (rs.next()) {
                    int count = rs.getInt(1);
                    //writer.println("Total number of users in the table : " + count);
                }

                getServletContext().getRequestDispatcher("/UserList.jsp").forward(request, response);
            }
        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {
            writer.close();
        }

        //getServletContext().getRequestDispatcher("/UserList.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.html").forward(request, response);
    }
}

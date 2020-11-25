package app.dao;

import app.dao.exception.DAOException;
import app.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class UserDAOImpl implements UserDAO {

    private static Map<Integer, User> users = new HashMap<>();
    private static List<User> usrs;

    @Override
    public List<User> getAllUsers() throws DAOException {
        try {
            try(Connection connection = getConnection())
            {
                /*
                Statement stmt = connection.createStatement();
                String selectQuery = "select * from users";
                ResultSet rs = stmt.executeQuery(selectQuery);
                */
            }
        } catch (Exception e) {
            // НЕОБХОДИМО ПРАВИЛЬНО ОБРАБОТАТЬ ИСКЛЮЧЕНИЕ!!!
            e.printStackTrace();
        }
        return new ArrayList<User>(users.values());
    }

    @Override
    public void registration(User user) throws DAOException {

        // Реализация метода регистрации.
    }

    @Override
    public void create(User user) throws DAOException {

        users.put(user.getId(), user);
    }

    @Override
    public User retrieve(int id) throws DAOException {

        return users.get(id);
    }

    @Override
    public void update(User user) throws DAOException {

        users.put(user.getId(), user);
    }

    @Override
    public void delete(User user) throws DAOException {

        users.remove(user.getId());
    }

    private Connection getConnection() throws SQLException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("db.properties");
        Properties dbProperties = new Properties();
        try {
            dbProperties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = dbProperties.getProperty("url");
        String name = dbProperties.getProperty("username");
        String password = dbProperties.getProperty("password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, name, password);
    }

}

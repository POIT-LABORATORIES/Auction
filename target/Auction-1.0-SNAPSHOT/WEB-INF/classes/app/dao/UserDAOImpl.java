package app.dao;

import app.dao.exception.DAOException;
import app.data.ConnectionPool;
import app.models.User;

import javax.naming.NamingException;
import java.sql.*;
import java.util.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAllUsers() throws DAOException {
        try {
            List<User> userList = new ArrayList<>();
            try(Connection connection = getConnection()){
                Statement stmt = connection.createStatement();
                String selectQuery = "select * from users";
                ResultSet rs = stmt.executeQuery(selectQuery);
                while (rs.next()) {
                    int id = rs.getInt("user_id");
                    String name = rs.getString("user_name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    userList.add(new User(id, name, email, password));
                }
                rs.close();
                stmt.close();
            }
            return userList;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void registration(User user) throws DAOException {
        try{
            try(Connection connection = getConnection()){
                String selectQuery = "SELECT * FROM users WHERE email = ?";
                PreparedStatement ps = connection.prepareStatement(selectQuery);
                ps.setString(1, user.getEmail());
                ResultSet rs = ps.executeQuery();
                String password = "";
                if (rs.next()){
                    password = rs.getString("password");
                } else{
                    create(user);
                    return;
                }
                if (!user.getPassword().equals(password)){
                    throw new DAOException("Incorrect password");
                }
                rs.close();
                ps.close();
            }
        } catch (Exception e){
            throw new DAOException(e);
        }


        /*
        try{
            try(Connection connection = getConnection()){
                Statement stmt = connection.createStatement();
                String selectQuery = String.format("select * from users where email='%s'", user.getEmail());
                ResultSet rs = stmt.executeQuery(selectQuery);
                String password = "";
                if (rs.next()){
                    password = rs.getString("password");
                } else{
                    create(user);
                    return;
                }
                if (!user.getPassword().equals(password)){
                    throw new DAOException("Incorrect password");
                }
            }
        } catch (Exception e){
            throw new DAOException(e);
        }
        */
    }

    @Override
    public void create(User user) throws DAOException {
        try {
            try(Connection connection = getConnection()){
                String name = user.getName();
                String email = user.getEmail();
                String password = user.getPassword();
                String selectQuery = "INSERT INTO users " +
                        "(user_id, user_name, email, password, reg_date, last_reg_date) " +
                        "VALUES (NULL, ?, ?, ?, CURRENT_DATE(), CURRENT_DATE());";
                PreparedStatement ps = connection.prepareStatement(selectQuery);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot add new user to database");
                }
                ps.close();
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public User retrieve(int id) throws DAOException {
        try {
            String name;
            String email;
            String password;
            try(Connection connection = getConnection()){
                String selectQuery = "SELECT * FROM users WHERE user_id = ?";
                PreparedStatement ps = connection.prepareStatement(selectQuery);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    name = rs.getString("user_name");
                    email = rs.getString("email");
                    password = rs.getString("password");
                } else{
                    throw new DAOException(String.format("There is no user with id = %d", id));
                }
                rs.close();
                ps.close();
            }
            return new User(id, name, email, password);
        } catch (Exception e) {
            throw new DAOException(e);
        }


        /*
        try {
            String name;
            String email;
            String password;
            try(Connection connection = getConnection()){
                Statement stmt = connection.createStatement();
                String selectQuery = String.format("select * from users where user_id = %d", id);
                ResultSet rs = stmt.executeQuery(selectQuery);
                if (rs.next()){
                    name = rs.getString("user_name");
                    email = rs.getString("email");
                    password = rs.getString("password");
                } else{
                    throw new DAOException(String.format("There is no user with id = %d", id));
                }
            }
            return new User(id, name, email, password);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        */
    }

    @Override
    public void update(User user) throws DAOException {
        try {
            try(Connection connection = getConnection()){
                String updateQuery = "UPDATE users SET user_name = ?, " +
                        "email = ?, password = ? WHERE user_id = ?;";
                PreparedStatement ps = connection.prepareStatement(updateQuery);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setInt(4, user.getId());
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot update user in database");
                }
                ps.close();
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }


        /*
        try {
            try(Connection connection = getConnection()){
                Statement stmt = connection.createStatement();
                int id = user.getId();
                String name = user.getName();
                String email = user.getEmail();
                String password = user.getPassword();
                String selectQuery = String.format("update users set user_name = '%s', " +
                        "email = '%s', password = '%s' where user_id = %d", name, email, password, id);
                if (stmt.executeUpdate(selectQuery) == 0){
                    throw new DAOException("Cannot update user in database");
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        */
    }

    @Override
    public void delete(User user) throws DAOException {
        try {
            try(Connection connection = getConnection()){
                String deleteQuery = "DELETE FROM users WHERE user_id = ?";
                PreparedStatement ps = connection.prepareStatement(deleteQuery);
                ps.setInt(1, user.getId());
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot delete user in database");
                }
                ps.close();
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private Connection getConnection() throws SQLException, NamingException {
        ConnectionPool pool = ConnectionPool.getInstance();
        return pool.getConnection();
    }



    /*
    // Подключение к БД без Connection Pool.
    private Connection getConnection() throws SQLException, ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
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
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        return DriverManager.getConnection(url, name, password);
    }
    */

}

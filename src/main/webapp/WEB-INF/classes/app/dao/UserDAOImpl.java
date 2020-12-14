package app.dao;

import app.dao.exception.DAOException;
import app.data.DB;
import app.data.DBUtil;
import app.models.User;

import java.sql.*;
import java.util.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAllUsers() throws DAOException {
        PreparedStatement ps = null;
        try {
            List<User> userList = new ArrayList<>();
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT * FROM users";
                ps = connection.prepareStatement(selectQuery);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("user_id");
                    String name = rs.getString("user_name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    userList.add(new User(id, name, email, password));
                }
            }
            return userList;
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public void registration(User user) throws DAOException {
        PreparedStatement ps = null;
        try{
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT * FROM users WHERE email = ?";
                ps = connection.prepareStatement(selectQuery);
                ps.setString(1, user.getEmail());
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    throw new DAOException("The email is already in use");
                } else{
                    create(user);
                }
            }
        } catch (Exception e){
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public boolean emailExists(String email) throws DAOException {
        PreparedStatement ps = null;
        try {
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT email FROM users WHERE email = ?;";
                ps = connection.prepareStatement(selectQuery);
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public User authorization(User user) throws DAOException {
        PreparedStatement ps = null;
        try{
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT * FROM users WHERE email = ?";
                ps = connection.prepareStatement(selectQuery);
                ps.setString(1, user.getEmail());
                ResultSet rs = ps.executeQuery();
                String password = "";
                if (rs.next()){
                    password = rs.getString("password");
                } else{
                    throw new DAOException("Incorrect email");
                }
                if (!user.getPassword().equals(password)){
                    throw new DAOException("Incorrect password");
                }
                user = retrieve(rs.getInt("user_id"));
            }
        } catch (Exception e){
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
        return user;
    }

    @Override
    public void create(User user) throws DAOException {
        PreparedStatement ps = null;
        try {
            try(Connection connection = DB.getConnection()){
                String name = user.getName();
                String email = user.getEmail();
                String password = user.getPassword();
                String selectQuery = "INSERT INTO users " +
                        "(user_id, user_name, email, password, reg_date, last_reg_date) " +
                        "VALUES (NULL, ?, ?, ?, CURRENT_DATE(), CURRENT_DATE());";
                ps = connection.prepareStatement(selectQuery);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot add new user to database");
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public User retrieve(int id) throws DAOException {
        PreparedStatement ps = null;
        try {
            String name;
            String email;
            String password;
            try(Connection connection = DB.getConnection()){
                String selectQuery = "SELECT * FROM users WHERE user_id = ?";
                ps = connection.prepareStatement(selectQuery);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
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
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        PreparedStatement ps = null;
        try {
            try(Connection connection = DB.getConnection()){
                String updateQuery = "UPDATE users SET user_name = ?, " +
                        "email = ?, password = ? WHERE user_id = ?;";
                ps = connection.prepareStatement(updateQuery);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setInt(4, user.getId());
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot update user in database");
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        PreparedStatement ps = null;
        try {
            try(Connection connection = DB.getConnection()){
                String deleteQuery = "DELETE FROM users WHERE user_id = ?";
                ps = connection.prepareStatement(deleteQuery);
                ps.setInt(1, id);
                if (ps.executeUpdate() == 0){
                    throw new DAOException("Cannot delete user in database");
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally{
            DBUtil.closeStatement(ps);
        }
    }
}

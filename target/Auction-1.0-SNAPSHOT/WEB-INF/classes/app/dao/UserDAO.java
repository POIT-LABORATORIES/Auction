package app.dao;

import app.dao.exception.DAOException;
import app.models.User;

import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getAllUsers() throws DAOException;
    void registration(User user) throws DAOException;
    boolean emailExists(String email) throws DAOException;
}

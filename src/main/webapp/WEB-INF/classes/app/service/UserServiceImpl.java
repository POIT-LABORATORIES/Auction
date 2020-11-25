package app.service;

import app.dao.DAOFactory;
import app.dao.UserDAO;
import app.dao.UserDAOImpl;
import app.models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl() {
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        userDAO = daoObjectFactory.getUserDAO();
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void add(User user) {
        if (user != null){
            userDAO.create(user);
        }
    }

    @Override
    public void delete(User user) {
        if (user != null){
            userDAO.delete(user);
        }
    }

    @Override
    public void edit(User user) {
        if (user != null){
            userDAO.update(user);
        }
    }

    @Override
    public User getById(int id) {
        return userDAO.retrieve(id);
    }

    @Override
    public void registration(User user) {
        if (user != null){
            userDAO.registration(user);
        }
    }
}

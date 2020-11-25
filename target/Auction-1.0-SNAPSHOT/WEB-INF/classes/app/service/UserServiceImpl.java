package app.service;

import app.dao.UserDAO;
import app.dao.UserDAOImpl;
import app.models.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void add(User user) {
        userDAO.create(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public void edit(User user) {
        userDAO.update(user);
    }

    @Override
    public User getById(int id) {
        return userDAO.retrieve(id);
    }
}

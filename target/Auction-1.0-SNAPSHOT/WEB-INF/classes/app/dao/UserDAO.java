package app.dao;

import app.models.User;

import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getAllUsers();
    /*
    public List<User> getAllUsers();
    public void addUser(User user);
    public void removeUser(User user);
    public void updateUser(User user);
    public User getUserById(int id);
    */
}

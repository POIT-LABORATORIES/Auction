package app.service;

import app.models.User;
import app.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> getAllUsers() throws ServiceException;
    void addUser(User film) throws ServiceException;
    void deleteUser(User film) throws ServiceException;
    void editUser(User film) throws ServiceException;
    User getUserById(int id) throws ServiceException;
    void registration(User user) throws ServiceException;
}

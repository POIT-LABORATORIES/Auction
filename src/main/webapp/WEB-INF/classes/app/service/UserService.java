package app.service;

import app.models.User;
import app.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> getAllUsers() throws ServiceException;
    void addUser(User user) throws ServiceException;
    void deleteUser(int id) throws ServiceException;
    void editUser(User user) throws ServiceException;
    User getUserById(int id) throws ServiceException;
    void registration(User user) throws ServiceException;
    User authorization(User user) throws ServiceException;
}

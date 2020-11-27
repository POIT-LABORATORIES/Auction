package app.service;

import app.models.User;
import app.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> getAllUsers() throws ServiceException;
    void add(User film) throws ServiceException;
    void delete(User film) throws ServiceException;
    void edit(User film) throws ServiceException;
    User getById(int id) throws ServiceException;
    void registration(User user) throws ServiceException;
}

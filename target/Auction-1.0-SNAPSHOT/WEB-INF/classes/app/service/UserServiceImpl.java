package app.service;

import app.dao.DAOFactory;
import app.dao.UserDAO;
import app.dao.exception.DAOException;
import app.models.User;
import app.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl() {
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        userDAO = daoObjectFactory.getUserDAO();
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            List<User> userList = userDAO.getAllUsers();
            return userList;
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(User user) throws ServiceException {
        try {
            if (user != null){
                userDAO.create(user);
            }
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(User user) throws ServiceException {
        try {
            if (user != null){
                userDAO.delete(user);
            }
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void edit(User user) throws ServiceException {
        try {
            if (user != null){
                userDAO.update(user);
            }
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public User getById(int id) throws ServiceException {
        try {
            return userDAO.retrieve(id);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void registration(User user) throws ServiceException {
        try {
            if (user != null){
                userDAO.registration(user);
            }
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}

package app.service;

import app.models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void add(User film);
    void delete(User film);
    void edit(User film);
    User getById(int id);
    void registration(User user);
}

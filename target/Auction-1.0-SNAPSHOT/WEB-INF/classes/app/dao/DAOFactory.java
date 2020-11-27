package app.dao;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO UserDAOImpl = new UserDAOImpl();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDAO getUserDAO(){
        return UserDAOImpl;
    }
}

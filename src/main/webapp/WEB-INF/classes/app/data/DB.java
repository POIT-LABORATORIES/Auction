package app.data;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class DB {
    public static Connection getConnection() throws SQLException, NamingException {
        ConnectionPool pool = ConnectionPool.getInstance();
        return pool.getConnection();
    }
}

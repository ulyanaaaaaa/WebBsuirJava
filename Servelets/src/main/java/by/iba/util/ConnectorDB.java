package by.iba.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConnectorDB {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = ConnectorDB.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            if (input == null) {
                throw new IOException("Unable to find db.properties");
            }
            props.load(input);
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
            URL = props.getProperty("db.url");
        } catch (IOException e) {
            throw new RuntimeException("Could not load database properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

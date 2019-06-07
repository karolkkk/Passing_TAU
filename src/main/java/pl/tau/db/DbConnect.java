package pl.tau.db;

/**
 * Created by Owner on 07/06/2019.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnect {
    private static Connection connection = null;

    public static Connection getCon() {

        if(connection != null) {
            return connection;
        }
        String url = "jdbc:mysql://localhost:8085/tau_project?useLegacyDatetimeCode=false&useSSL=false&serverTimezone=Europe/Amsterdam";
        String username = "root";
        String password = "admin";
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username,password);
            if(connection == null) System.out.println("Connection failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

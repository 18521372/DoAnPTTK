package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung98
 */
public class DBConnect {

    public static String driver = "com.mysql.jdbc.Driver";
    public static String url = "jdbc:mysql://127.0.0.1:3306/quanlychtl?characterEncoding=utf8";
    public static String user = "root";
    public static String password = "root";

    
    public static Connection getConnection() {
        try {
            Connection connection = null;
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user, password);
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

   

}

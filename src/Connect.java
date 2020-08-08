import java.sql.*;
public class Connect {
    Connection c;
    Statement s;
     public Connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/db/user");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}

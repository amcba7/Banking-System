/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system;

/**
 *
 * @author om
 */
import java.sql.*;
public class Connect {
    Connection c;
    Statement s;
    public Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///MyProject","root","root");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static Connection buildConnection () {
        try{
           Class.forName("com.mysql.jdbc.Driver");
           try {
               return DriverManager.getConnection("jdbc:mysql:///MyProject","root","root");
           } catch (SQLException e) {
               System.err.println("Problem in connection");
               e.printStackTrace();
                return null; // better throw an exception
           }
        } catch(ClassNotFoundException ex) {
            System.err.println("Error loading driver");
            return null; // better throw an exception
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laptop
 */
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class javaconnection {
    Connection c;


public static Connection connectDb(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/if17_martandr?user=if17&password=if17");
        return c;
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        return null;
    }
 }
}

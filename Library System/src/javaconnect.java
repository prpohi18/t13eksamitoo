/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author denzz
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class javaconnect {
    Connection conn;
    /*private static final String USERNAME="if17";
    private static final String PASSWORD="if17";
    private static final String CONN_STRING=
            "jdbc:mysql://greeny.cs.tlu.ee/phpmyadmin/index.php?db=if17_sulgdenn?"; */   
    public static Connection ConnecrDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/if17_sulgdenn?user=if17&password=if17");
               return conn;
                /*Connection conn=DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            return conn;*/
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
}
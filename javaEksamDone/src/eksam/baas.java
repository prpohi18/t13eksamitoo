package eksam;

import java.sql.*;

public class baas {
    public static void main(String[] arg) throws Exception{
        double SISESTUS = 11.53;
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection(
             "jdbc:mysql://localhost:3306/test1","root","b1m2w3");
            
            if(conn != null){
            System.out.println("Ühendus loodud!");
            
            String sql_käsk = "INSERT INTO eksam (hind) VALUES (?)";
            //String sql_käsk = "SELECT hind FROM eksam";
            PreparedStatement statement = conn.prepareStatement(sql_käsk);

            statement.setDouble(1, SISESTUS);
            statement.executeUpdate();
            System.out.println("Andmed baasi lisatud!");

            }
            
            
            
        } catch (Exception e){
            System.out.println("NOT connected!");
            }

                
                


//        if(arg.length==2){
//          lisaBaasi.lisa(conn, arg);
//        }
//        Statement st=conn.createStatement();
//        ResultSet rs=st.executeQuery("SELECT id, raadius, mass FROM kapsad");
//        while(rs.next()){
//            System.out.println(rs.getInt("id")+" "+rs.getDouble("raadius")+" "+rs.getDouble("mass"));
//        }
    }
}


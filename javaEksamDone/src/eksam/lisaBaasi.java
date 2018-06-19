package eksam;
import java.sql.*;

public class lisaBaasi {
    public static void lisa(Connection connect, String[] arg) throws Exception{        
        PreparedStatement st=connect.prepareStatement("INSERT INTO eksam (hind) VALUES(?)");
        st.setDouble(1, Double.parseDouble(arg[0]));
        st.executeUpdate();
    }    
    
    
    
}

/*

package lisaBaasi;
import java.sql.*;
public class Lisamine{
    public static void lisa(Connection cn, String[] arg) throws Exception{        
        PreparedStatement st=cn.prepareStatement("INSERT INTO kapsad VALUES(DEFAULT, ?, ?)");
        st.setDouble(1, Double.parseDouble(arg[0]));
        st.setDouble(2, Double.parseDouble(arg[1]));
        st.executeUpdate();
    }
}

*/
package baas;
import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@RestController
@SpringBootApplication
public class Baas {
    String url = "jdbc:mysql://localhost/if17_kellrasm?user=if17&password=if17";

    @RequestMapping("/")
    String rootFolder(){
        return "root";
    }
    @RequestMapping("/loadSelect")
    String getList()throws Exception{
        Connection cn=DriverManager.getConnection(url);
        PreparedStatement st=cn.prepareStatement("SELECT DISTINCT artist FROM javaeksam");
        ResultSet rs=st.executeQuery();

        String allNames;
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        while(rs.next()) {
            JSONObject item = new JSONObject();
            item.put("artist", rs.getString("artist"));
            array.add(item);
        }
        cn.close();

        json.put("query", array);
        allNames = json.toString();
        return allNames;
    }

    @RequestMapping("/loadSongs")
    String getSongs(String artist)throws Exception{
        Connection cn=DriverManager.getConnection(url);
        PreparedStatement st=cn.prepareStatement("SELECT name FROM javaeksam WHERE artist=?");
        st.setString(1, artist);
        ResultSet rs=st.executeQuery();

        String allSongs;
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        while(rs.next()) {
            JSONObject item = new JSONObject();
            item.put("name", rs.getString("name"));
            array.add(item);
        }
        cn.close();

        json.put("query", array);
        allSongs = json.toString();
        return allSongs;
    }
    @RequestMapping("/k")
    String lyricsGet(String song)throws Exception{
        String lyrics;
        Connection cn=DriverManager.getConnection(url);
        PreparedStatement st=cn.prepareStatement("SELECT lyrics FROM javaeksam WHERE name=?");
        st.setString(1, song);
        ResultSet rs=st.executeQuery();
        rs.next();
        lyrics = rs.getString("lyrics");
        cn.close();
        return lyrics;
    }

    @RequestMapping("/c")
    String counterGet(String name, String artist, String lyrics)throws Exception{
        Connection cn=DriverManager.getConnection(url);
        PreparedStatement st=cn.prepareStatement("INSERT INTO javaeksam (name, artist, lyrics) VALUES (?, ?, ?)");
        st.setString(1, name);
        st.setString(2, artist);
        st.setString(3, lyrics);
        st.executeUpdate();
        cn.close();
        return "Laul lisatud";
    }

    public static void main(String[] args) {
        SpringApplication.run(Baas.class, args);
    }
}

// java -jar -Dserver.port=9123 target/t11kontrolltoo-1.0.jar
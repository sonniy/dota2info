package org.all.info.parser;



import org.all.info.model.LobbyType;
import org.all.info.util.ConnectionFactory;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/* The class gets all lobby types from json file in "resources/data" folder */
public class LobbyTypeParser {

    private static Logger log = LogManager.getLogger(LobbyTypeParser.class);

    private final String JSON_PATH = "E:\\workspace\\dota2info\\src\\main\\resources\\data\\lobbyTypes.json";


    public void saveLobbyTypes(){
        String sql;
        try (Connection con = ConnectionFactory.getConnection();
             Statement st = con.createStatement()){

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(new FileReader(new File(JSON_PATH)));
            JSONArray lobbyTypes = (JSONArray) root.get("lobbyTypes");

            for (int i = 0; i < lobbyTypes.size(); i++) {
                JSONObject lobbyType = (JSONObject) lobbyTypes.get(i);
                /* Checking for for matches */
                sql = "SELECT  * FROM lobbyTypes WHERE name = '" + lobbyType.get("name") + "';";
                ResultSet rs = st.executeQuery(sql);
                if (!rs.next()){
                    /* if rs.next false then matches have not been found, inserting the lobby type name */
                    sql = "INSERT INTO lobbyTypes(name) VALUES('" + lobbyType.get("name") + "');";
                    if (!st.execute(sql)){
                        log.info("Game mode has been saved");
                    }else {
                        log.info("ERROR");
                    }
                } else{
                    log.info("The game type is already exists");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

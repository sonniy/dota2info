package org.all.info.parser;

import org.all.info.util.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* The class gets all game modes from json file in "resources/data" folder */
public class GameModeParser {

    private static Logger log = LogManager.getLogger(GameModeParser.class);

    private final String JSON_PATH = "E:\\workspace\\dota2info\\src\\main\\resources\\data\\gameModes.json";


    public void saveGameMode(){
        String sql;
        try (Connection con = ConnectionFactory.getConnection();
             Statement st = con.createStatement()){

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(new FileReader(new File(JSON_PATH)));
            JSONArray gameModes = (JSONArray) root.get("gameModes");

            for (int i = 0; i < gameModes.size(); i++) {
                JSONObject gameMode = (JSONObject) gameModes.get(i);
                /* Checking for for matches */
                sql = "SELECT  * FROM gameModes WHERE name = '" + gameMode.get("name") + "';";
                ResultSet rs = st.executeQuery(sql);
                if (!rs.next()){
                    /* if rs.next false then matches have not been found, inserting the game mode name */
                    sql = "INSERT INTO gameModes(name) VALUES('" + gameMode.get("name") + "');";
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

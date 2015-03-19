package org.all.info.parser;



import org.all.info.model.match.LobbyType;
import org.all.info.service.match.LobbyTypeService;
import org.all.info.util.ConnectionFactory;
import org.all.info.util.SpringUtil;
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


/* The class gets all lobby types from json file in "resources/data" folder */
public class LobbyTypeParser {

    private static Logger log = LogManager.getLogger(LobbyTypeParser.class);

    private final String JSON_PATH = "/home/yuriygorbylov/workspace/dota2all-info/src/main/resources/data/lobbyTypes.json";

    private LobbyTypeService lobbyTypeService = (LobbyTypeService) SpringUtil.getApplicationContext().getBean("lobbyTypeService");


    public void saveLobbyTypes(){

        try {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(new FileReader(new File(JSON_PATH)));
            JSONArray lobbyTypes = (JSONArray) root.get("lobbyTypes");

            for (int i = 0; i < lobbyTypes.size(); i++) {
                JSONObject JSONLobbyType = (JSONObject) lobbyTypes.get(i);
                Long id = (Long) JSONLobbyType.get("id");
                String name = (String) JSONLobbyType.get("name");
                /* Checking for for matches */
                LobbyType check = lobbyTypeService.read(id.intValue());

                if (check == null){
                    /* if rs.next false then matches have not been found, inserting the lobby type name */
                    LobbyType lobbyType = new LobbyType(id.intValue(), name);
                    lobbyTypeService.save(lobbyType);
                    log.info("Lobby type been saved");
                } else{
                    log.info("The lobby type is already exists");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

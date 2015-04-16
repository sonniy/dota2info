package org.all.info.parser.match;

import org.all.info.model.match.GameMode;
import org.all.info.service.match.GameModeService;
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

/* The class gets all game modes from json file in "resources/data" folder */
public class GameModeParser {

    private static Logger log = LogManager.getLogger(GameModeParser.class);

    private final String JSON_PATH = "E:\\workspace\\dota2all-info\\src\\main\\resources\\data\\gameModes.json";

    private GameModeService gameModeService = (GameModeService) SpringUtil.getApplicationContext().getBean("gameModeService");

    public void saveGameMode(){
        try{
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(new FileReader(new File(JSON_PATH)));
            JSONArray gameModes = (JSONArray) root.get("gameModes");

            for (int i = 0; i < gameModes.size(); i++) {
                JSONObject JSONGameMode = (JSONObject) gameModes.get(i);
                Long id = (Long) JSONGameMode.get("id");
                String name = (String) JSONGameMode.get("name");
                /* Checking for matches */
                GameMode check = gameModeService.read(id.intValue());
                if (check == null){
                    /* if check == null then matches have not been found, inserting the game mode name */
                    GameMode gameMode = new GameMode(id.intValue(), name);
                    gameModeService.save(gameMode);
                    log.info("Game mode has been saved");
                } else{
                    log.info("The game type is already exists");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.all.info.parser;

import org.all.info.util.ConnectionFactory;
import org.all.info.util.HTTPClientUtil;
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

/**
 * Created by yuriy.gorbylev on 19.03.2015.
 */
public class LeagueParser {

    private String key = "0B9F3A6A9759528C543F28540C831C4A";

    private String url = "https://api.steampowered.com/IDOTA2Match_570/GetLeagueListing/v0001/?key=" + key;

    private static Logger log = LogManager.getLogger(GameModeParser.class);

    private final String JSON_PATH = "E:\\workspace\\dota2info\\src\\main\\resources\\data\\gameModes.json";


    public void saveGameMode(){
        String sql;
        try (Connection con = ConnectionFactory.getConnection();
             Statement st = con.createStatement()){

            JSONObject root = HTTPClientUtil.getPageContent(url);
            JSONObject result = (JSONObject) root.get("result");
            JSONArray leagues = (JSONArray) result.get("leagues");

            for (int i = 0; i < leagues.size(); i++) {
                JSONObject league = (JSONObject) leagues.get(i);
                /* Checking for for matches */
                sql = "SELECT  * FROM leagues WHERE id = " + league.get("leagueid") + ";";
                ResultSet rs = st.executeQuery(sql);
                if (!rs.next()){
                    /* if rs.next false then matches have not been found, inserting the league into DB */
                    String name = (String) league.get("name");
                    Long leagueid = (Long) league.get("leagueid");
                    String description = (String) league.get("description");
                    String tournamentURL = (String) league.get("tournament_url");
                    sql = String.format("INSERT INTO leagues VALUES(%d,'%s','%s','%s');",
                            leagueid, name, description, tournamentURL);
                    if (!st.execute(sql)){
                        log.info("League id:" + leagueid + " has been saved");
                    }else {
                        log.info("ERROR");
                    }
                } else{
                    log.info("The league is already exists");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package org.all.info.parser;

import org.all.info.model.match.League;
import org.all.info.service.match.LeagueService;
import org.all.info.util.ConnectionFactory;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LeagueParser {

    private String key = "0B9F3A6A9759528C543F28540C831C4A";

    private String url = "https://api.steampowered.com/IDOTA2Match_570/GetLeagueListing/v0001/?key=" + key;

    private static Logger log = LogManager.getLogger(League.class);

    private LeagueService leagueService = (LeagueService) SpringUtil.getApplicationContext().getBean("leagueService");


    public void saveGameMode(){

            JSONObject root = HTTPClientUtil.getPageContent(url);
            JSONObject result = (JSONObject) root.get("result");
            JSONArray leagues = (JSONArray) result.get("leagues");

            for (int i = 0; i < leagues.size(); i++) {
                JSONObject JSONLeague = (JSONObject) leagues.get(i);
                /* Checking for for matches */
                League check = leagueService.read((Long) JSONLeague.get("leagueid"));

                if (check == null){
                    /* if check != null false then matches have not been found, inserting the league into DB */
                    Long leagueid = (Long) JSONLeague.get("leagueid");
                    String name = (String) JSONLeague.get("name");
                    String description = (String)JSONLeague.get("description");
                    String tournamentURL = (String) JSONLeague.get("tournament_url");
                    League league = new League(leagueid, name, description, tournamentURL);
                    leagueService.save(league);
                    log.info("League id:" + leagueid + " has been saved");
                } else{
                    log.info("The league is already exists");
                }
            }
    }
}

package org.all.info.parser;

import org.all.info.model.GameMode;
import org.all.info.model.League;
import org.all.info.model.LobbyType;
import org.all.info.model.MatchID;
import org.all.info.service.match.MatchService;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class MatchParser implements Runnable {


    private MatchService matchService = (MatchService) SpringUtil.getApplicationContext().getBean("matchService");

    private static Logger log = LogManager.getLogger(MatchParser.class);

    private String key = "0B9F3A6A9759528C543F28540C831C4A";

    private String urlTools = "&match_id=";

    private String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v001/?key=" + key + urlTools;

    @Override
    public void run() {

        while (true){
            /* Getting last match_id from DB which was not parsed */
            Long lastMatchID = matchService.readLastMatchID();

            JSONObject root = HTTPClientUtil.getPageContent(url + lastMatchID);
            JSONObject result = (JSONObject) root.get("result");


            Long match_id = (Long) result.get("match_id");
            String season = (String) result.get("season");
            Boolean radiant_win = (Boolean) result.get("radiant_win");
            Long duration = (Long) result.get("duration");
            Long start_time = (Long) result.get("start_time");
            Integer tower_status_radiant = (Integer) result.get("tower_status_radiant");
            Integer tower_status_dire = (Integer) result.get("tower_status_dire");
            Integer barracks_status_radiant = (Integer) result.get("barracks_status_radiant");
            Integer barracks_status_dire = (Integer) result.get("barracks_status_dire");
            Integer cluster = (Integer) result.get("cluster");
            Integer first_blood_time = (Integer) result.get("first_blood_time");
            Integer human_players = (Integer) result.get("human_players");
            Integer positive_votes = (Integer) result.get("positive_votes");
            Integer negative_votes = (Integer) result.get("negative_votes");
            Integer picks_bans = (Integer) result.get("picks_bans");
            LobbyType lobbyType;
            GameMode gameMode;
            League league;




        }
    }
}

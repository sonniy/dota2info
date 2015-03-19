package org.all.info.parser;

import org.all.info.model.MatchID;
import org.all.info.model.match.GameMode;
import org.all.info.model.match.League;
import org.all.info.model.match.LobbyType;
import org.all.info.model.match.Match;
import org.all.info.service.match.GameModeService;
import org.all.info.service.match.LeagueService;
import org.all.info.service.match.LobbyTypeService;
import org.all.info.service.match.MatchService;
import org.all.info.service.matchID.MatchIDService;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import javax.swing.*;


public class MatchParser implements Runnable {


    private MatchService matchService = (MatchService) SpringUtil.getApplicationContext().getBean("matchService");
    private LobbyTypeService lobbyTypeService = (LobbyTypeService) SpringUtil.getApplicationContext().getBean("lobbyTypeService");
    private GameModeService gameModeService = (GameModeService) SpringUtil.getApplicationContext().getBean("gameModeService");
    private LeagueService leagueService = (LeagueService) SpringUtil.getApplicationContext().getBean("leagueService");
    private MatchIDService matchIDService = (MatchIDService) SpringUtil.getApplicationContext().getBean("matchIDService");

    private static Logger log = LogManager.getLogger(MatchParser.class);

    private String key = "0B9F3A6A9759528C543F28540C831C4A";

    private String urlTools = "&match_id=";

    private String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v001/?key=" + key + urlTools;

    @Override
    public void run() {

        while (true){
            /* Getting last match_id from DB which was not parsed */
            Long lastMatchID = matchIDService.readLastMatchID();

            JSONObject root = HTTPClientUtil.getPageContent(url + lastMatchID);
            JSONObject result = (JSONObject) root.get("result");

            Long match_id = (Long) result.get("match_id");
            //String season = (String) result.get("season");
            Boolean radiant_win = (Boolean) result.get("radiant_win");
            Long duration = (Long) result.get("duration");
            Long start_time = (Long) result.get("start_time");
            Long tower_status_radiant = (Long) result.get("tower_status_radiant");
            Long tower_status_dire = (Long) result.get("tower_status_dire");
            Long barracks_status_radiant = (Long) result.get("barracks_status_radiant");
            Long barracks_status_dire = (Long) result.get("barracks_status_dire");
            Long cluster = (Long) result.get("cluster");
            Long first_blood_time = (Long) result.get("first_blood_time");
            Long human_players = (Long) result.get("human_players");
            Long positive_votes = (Long) result.get("positive_votes");
            Long negative_votes = (Long) result.get("negative_votes");
            //Long picks_bans = (Long) result.get("picks_bans");
            Long lobbyTypeID = (Long) result.get("lobby_type");
            LobbyType lobbyType = lobbyTypeService.read(lobbyTypeID.intValue());
            Long gameModeID = (Long) result.get("game_mode");
            GameMode gameMode = gameModeService.read(gameModeID.intValue());
            League league = leagueService.read((Long) result.get("leagueid"));

            Match match = new Match(match_id, radiant_win, duration, start_time, tower_status_radiant,
                    tower_status_dire, barracks_status_radiant, barracks_status_dire, cluster, first_blood_time,
                    human_players, positive_votes, negative_votes, lobbyType, gameMode, league);

            matchService.saveMatch(match);
            MatchID matchID = matchIDService.read(lastMatchID);
            matchID.setIsParsed(true);
            matchIDService.update(matchID);
            log.info("MATCH " + lastMatchID + "HAS BEEN SAVED");
        }
    }
}

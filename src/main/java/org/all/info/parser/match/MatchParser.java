package org.all.info.parser.match;

import org.all.info.model.match.MatchID;
import org.all.info.model.match.GameMode;
import org.all.info.model.match.League;
import org.all.info.model.match.LobbyType;
import org.all.info.model.match.Match;
import org.all.info.service.match.GameModeService;
import org.all.info.service.match.LeagueService;
import org.all.info.service.match.LobbyTypeService;
import org.all.info.service.match.MatchService;
import org.all.info.service.match.MatchIDService;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

/* The class for parsing match details from DOTA2 API */
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

        while (!Thread.currentThread().isInterrupted()){
            /* Getting last match_id from DB which was not parsed */
            Long lastMatchID = matchIDService.readLastMatchID();

            JSONObject root = HTTPClientUtil.getPageContent(url + lastMatchID);
            JSONObject result = (JSONObject) root.get("result");

            Long match_id = (Long) result.get("match_id");
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

            /* Getting from tables (lobbyTypes, gameModes, leagues) existing object by IDs*/
            Long lobbyTypeID = (Long) result.get("lobby_type");
            LobbyType lobbyType = lobbyTypeService.read(lobbyTypeID.intValue());
            Long gameModeID = (Long) result.get("game_mode");
            GameMode gameMode = gameModeService.read(gameModeID.intValue());
            League league = leagueService.read((Long) result.get("leagueid"));

            /* Creating match object */
            Match match = new Match(match_id, radiant_win, duration, start_time, tower_status_radiant,
                    tower_status_dire, barracks_status_radiant, barracks_status_dire, cluster, first_blood_time,
                    human_players, positive_votes, negative_votes, lobbyType, gameMode, league);

            /* Saving the match to DB */
            matchService.saveMatch(match);
            /* Marking in matchID table that this match has been parsed */
            MatchID matchID = matchIDService.read(lastMatchID);
            matchID.setIsParsed(true);
            matchIDService.update(matchID);
            log.info("MATCH ID:" + lastMatchID + " HAS BEEN SAVED");
        }
    }
}

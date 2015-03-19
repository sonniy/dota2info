package org.all.info.parser;

import org.all.info.model.match.League;
import org.all.info.service.match.LeagueService;
import org.all.info.service.match.MatchService;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Run {

    private static Logger log = LogManager.getLogger(Run.class);

    public static void main(String[] args) {

//        new LeagueParser().saveGameMode();
//        new LobbyTypeParser().saveLobbyTypes();
//        new GameModeParser().saveGameMode();
//
//        new MatchIDParser().run();

        new MatchParser().run();


    }
}

package org.all.info.parser;


import org.all.info.parser.match.MatchIDParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Run {

    private static Logger log = LogManager.getLogger(Run.class);

    public static void main(String[] args) {

//        new LeagueParser().saveGameMode();
//        new LobbyTypeParser().saveLobbyTypes();
//        new GameModeParser().saveGameMode();

        new MatchIDParser().run();

        //new MatchParser().run();


    }
}

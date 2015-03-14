package org.all.info.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.Date;

/**
 * Created by Мартюк on 05.03.2015.
 */
public class SearchBattle {

    private static Logger LOG = LogManager.getLogger(SearchBattle.class);

    private static String URL = "http://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V1/" +
            "?language=ru_RU" +
            "&key=A4876DA2088DCB48058C61EC6200F143" +
            "&format=json" +
            "&match_id="; //326607718

    public static void main(String[] args) {
        int j = 1160906154/100;
        int m = j/60;
        int h = m/24;
        int d = h/30;
//        int d =
        System.out.println("sec " + j);
        System.out.println("min " + m);
        System.out.println("h " + h);
        System.out.println("d " + d);
//        parseBattleID();

    }

    private static void parseBattleID() {
        int i = 1294745715;
//        while (i++ >= 0) {

        JSONObject root = TestHttpClient.getPageContent(URL + i);
        JSONObject result = (JSONObject) root.get("result");
        String error = (String) result.get("error");

        if (null != error) {
            LOG.error(String.format(
                    "[ID: %1$d, " +
                            "error: %2$s, ",
                    i,
                    error));
        } else {
            Boolean radiant_win = (Boolean) result.get("radiant_win");
            Long match_seq_num = (Long) result.get("match_seq_num");
            Long start_time = (Long) result.get("start_time");
            LOG.info(String.format(
                    "[ID: %1$d, " +
                            "radiant_win: %2$b, " +
                            "match_seq_num: %3$d, " +
                            "start_time: %4$tY.%4$tm.%4$td %4$tH:%4$tM:%4$tS.%4$tL]",
                    i,
                    radiant_win,
                    match_seq_num,
                    new Date(start_time*1000)));
        }
//        }
    }
}

//

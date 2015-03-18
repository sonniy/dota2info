package org.all.info.parser;

import org.all.info.dao.matchID.MatchIDDAO;
import org.all.info.model.MatchID;
import org.all.info.service.matchID.MatchIDService;
import org.all.info.util.ConnectionFactory;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* The class for parsing match_seq_num and match_id */
public class MatchIDParser implements Runnable{

    private MatchIDService matchIDService = (MatchIDService) SpringUtil.getApplicationContext().getBean("matchIDService");

    private static Logger log = LogManager.getLogger(MatchIDParser.class);

    private String key = "0B9F3A6A9759528C543F28540C831C4A";

    private String urlTools = "&start_at_match_seq_num=";

    private String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v0001/?key=" + key + urlTools;

    @Override
    public void run() {
        /* Getting last match_seq_num from DB */
        Long lastMatchSeqNum = matchIDService.readLastMatchSeqNum();
        while (true){
            /* Getting JSON object with match_id information */
            JSONObject root = HTTPClientUtil.getPageContent(url+ lastMatchSeqNum);
            JSONObject result = (JSONObject) root.get("result");
            JSONArray matches = (JSONArray) result.get("matches");
            for (int i = 0; i < matches.size(); i++){
                JSONObject seqMatch = (JSONObject) matches.get(i);
                /* Getting match_id */
                Long matchID = (Long) seqMatch.get("match_id");
                Long matchSeqNum =  (Long) seqMatch.get("match_seq_num");
                /* Checking for the same match_seq_num */
                if (matchIDService.isMatchExist(matchSeqNum)){
                    log.info("[ THE MATCH is not available ]");
                } else{
                    /* Adding match_id to DB */
                    MatchID mID = new MatchID(matchSeqNum, matchID, false);
                    matchIDService.saveMatchID(mID);
                    log.info(String.format("[ THE MATCH: { match_id: %d, match_seq_num: %d } HAS BEEN ADDED ]", matchID, matchSeqNum));
                }
                lastMatchSeqNum++;
            }
        }
    }
}
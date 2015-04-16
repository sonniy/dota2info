package org.all.info.parser.match;

import org.all.info.model.match.MatchID;
import org.all.info.service.match.MatchIDService;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/* The class for parsing match_seq_num and match_id */
public class MatchIDParser implements Runnable{

    private static Logger log = LogManager.getLogger(MatchIDParser.class);

    private MatchIDService matchIDService = (MatchIDService) SpringUtil.getApplicationContext().getBean("matchIDService");

    private String key = "0B9F3A6A9759528C543F28540C831C4A";

    private String urlTools = "&start_at_match_seq_num=";

    private String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v0001/?key=" + key + urlTools;

    @Override
    public void run() {


        while (!Thread.currentThread().isInterrupted()){
            /* Getting last match_seq_num from DB and taking the next*/
            Long lastMatchSeqNum = matchIDService.readLastMatchSeqNum() + 1;
            /* Getting JSON object with match_id information */
            JSONObject root = HTTPClientUtil.getPageContent(url+ lastMatchSeqNum);
            JSONObject result = (JSONObject) root.get("result");
            JSONArray matches = (JSONArray) result.get("matches");
            for (int i = 0; i < matches.size(); i++){
                JSONObject seqMatch = (JSONObject) matches.get(i);
                /* Getting match_id and match_seq_num*/
                Long matchID = (Long) seqMatch.get("match_id");
                Long matchSeqNum =  (Long) seqMatch.get("match_seq_num");
                /* Checking for the same match_seq_num */
                if (matchIDService.isMatchExist(matchSeqNum)){
                    log.info("[ THE MATCH is not available ]");
                } else{
                    /* Adding matchID object to DB */
                    MatchID mID = new MatchID(matchSeqNum, matchID, false);
                    matchIDService.saveMatchID(mID);
                    log.info(String.format("[ THE MATCH: { match_id: %d, match_seq_num: %d } HAS BEEN ADDED ]", matchID, matchSeqNum));
                }
            }
        }
    }
}

package org.all.info.matchIDParser;

import org.all.info.util.ConnectionFactory;
import org.all.info.util.HTTPClientUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SearchMatchID {

    private static Logger log = LogManager.getLogger(SearchMatchID.class);

    private String key = "?key=0B9F3A6A9759528C543F28540C831C4A";

    private String urlTools = "&matches_requested=1&start_at_match_seq_num=";

    private String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v0001/" + key + urlTools;


    public void findMatchID(){

        /* Getting last match_seq_num from DB */
        Long lastMatchSeqNum = getLastSeqMatch();

        while (true){
            /* Getting JSON object with match_id information */
            JSONObject root = HTTPClientUtil.getPageContent(url+ lastMatchSeqNum);
            JSONObject result = (JSONObject) root.get("result");
            JSONArray matches = (JSONArray) result.get("matches");
            JSONObject seqMatch = (JSONObject) matches.get(0);

            /* Getting match_id */
            Long matchID = (Long) seqMatch.get("match_id");
            Long matchSeqNum =  (Long) seqMatch.get("match_seq_num");

            /* Adding match_id to DB */
            addMatchID(matchID, matchSeqNum);

            lastMatchSeqNum++;
        }

    }

    private void addMatchID(Long matchID, Long matchSeqNum){

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            st = con.createStatement();
            /* Checking for the same match_seq_num in DB */
            String sql = "SELECT match_seq_num FROM matchID WHERE match_seq_num =" + matchSeqNum;
            rs = st.executeQuery(sql);

            /* if rs.next = "true" then the match_seq_num is already in DB */
            if (rs.next()){
                log.info(String.format("[ THE MATCH: { match_id: %d, match_seq_num: %d } is not available ]", matchID, matchSeqNum));
            } else {
                sql = String.format("INSERT INTO matchID VALUES(%d, %d)", matchSeqNum, matchID);
                st.executeUpdate(sql);
                log.info(String.format("[ THE MATCH: { match_id: %d, match_seq_num: %d } HAS BEEN ADDED ]", matchID, matchSeqNum));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private Long getLastSeqMatch(){

        Long lastMatchSeqNum = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            st = con.createStatement();
            String sql = "SELECT MAX(match_seq_num) FROM matchID LIMIT 1";
            rs = st.executeQuery(sql);
            if (rs.next()){
                lastMatchSeqNum = rs.getLong("MAX(match_seq_num)");
            } else{
                log.info("[ Empty database! ]");
            }

            return lastMatchSeqNum;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new SearchMatchID().findMatchID();
    }
}

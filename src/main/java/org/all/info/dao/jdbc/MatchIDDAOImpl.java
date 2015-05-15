package org.all.info.dao.jdbc;

import org.all.info.dao.MatchIDDAO;
import org.all.info.model.match.MatchID;
import org.all.info.util.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;



public class MatchIDDAOImpl implements MatchIDDAO{

    private final Logger log = LogManager.getLogger(this.getClass());


    /*
    *
    *
    * */
    @Override
    public void save(MatchID matchID) throws Exception {
        if (isMatchIDExist(matchID.getMatch_id())){
            log.error(String.format("The match [match_id: %d] already exists", matchID.getMatch_id()));
        } else{
            String saveSQL = "INSERT INTO matchID VALUES(? , ? , ?) ";
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(saveSQL)){
                statement.setLong(1, matchID.getMatch_seq_num());
                statement.setLong(2, matchID.getMatch_id());
                statement.setBoolean(3, matchID.getIsParsed());
                statement.execute();
                log.info(String.format("The entity has been saved [match_id: %d, match_seq_num: %d]",
                            matchID.getMatch_id(), matchID.getMatch_seq_num()));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [match_id: %d]", matchID.getMatch_id()));
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [match_id: %d]", matchID.getMatch_id()));
            }
        }

    }

    /*
    *
    * */
    @Override
    public MatchID read(Long matchID) throws Exception {
        long match_seq_num;
        long match_id;
        boolean isParsed;
        String readSQL = "SELECT * FROM matchID WHERE match_id = " + matchID + " LIMIT 1";
        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readSQL)) {
            if (resultSet.next()){
                match_seq_num = resultSet.getLong("match_seq_num");
                match_id = resultSet.getLong("match_id");
                isParsed = resultSet.getBoolean("isParsed");
                return new MatchID(match_seq_num, match_id, isParsed);
            } else{
                log.error(String.format("The match [match_id: %d] has not found", matchID));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    *
    * */
    @Override
    public Long readLastMatchSeqNum() {
        String readSQL = "SELECT match_seq_num FROM matchID ORDER BY match_seq_num DESC LIMIT 1";
        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readSQL)) {
            if (resultSet.next()){
                return resultSet.getLong("match_seq_num");
            } else{
                log.error("The table is empty");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    /*
    *
    * */
    @Override
    public Long readLastMatchID() {
        String readSQL = "SELECT match_id FROM matchID WHERE isParsed = 0 ORDER BY match_id ASC LIMIT 1";
        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readSQL)) {
            if (resultSet.next()){
                return resultSet.getLong("match_id");
            } else{
                log.error("The table is empty");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    /*
    *
    * */
    @Override
    public void update(MatchID matchID) {

        if (isMatchIDExist(matchID.getMatch_id())){
            String updateSQL = String.format("UPDATE matchID SET isParsed = %b WHERE match_seq_num = %d",
                    matchID.getIsParsed(), matchID.getMatch_seq_num());
            try(Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.createStatement()) {
                statement.execute(updateSQL);
                log.info(String.format("The entity has been updated [match_id: %d, match_seq_num: %d, isParsed: %b ]",
                        matchID.getMatch_id(), matchID.getMatch_seq_num(), matchID.getIsParsed()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    *
    * */
    @Override
    public Boolean isMatchSeqNumExist(Long matchSeqNum) {
        String readSQL = String.format("SELECT * FROM matchID WHERE match_seq_num = %d LIMIT 1", matchSeqNum);
        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readSQL)) {
            if (resultSet.next()) {
                return true;
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    *
    * */
    private boolean isMatchIDExist(long matchID){
        String readSQL = "SELECT * FROM matchID WHERE match_id = " + matchID + " LIMIT 1";
        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readSQL)) {
            if (resultSet.next()) {
                return true;
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

package org.all.info.dao.jdbc;

import org.all.info.dao.MatchDAO;
import org.all.info.model.match.GameMode;
import org.all.info.model.match.League;
import org.all.info.model.match.LobbyType;
import org.all.info.model.match.Match;
import org.all.info.util.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MatchDAOImpl implements MatchDAO {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void save(Match match) {
        if (isMatchExist(match.getMatch_id())){
            log.error(String.format("The Match [%d] already exists", match.getMatch_id()));
        } else {
            String saveSQL = "INSERT INTO matches VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(saveSQL)){
                statement.setLong(1, match.getMatch_id());
                statement.setBoolean(2, match.getRadiant_win());
                statement.setLong(3, match.getDuration());
                statement.setLong(4, match.getStart_time());
                statement.setInt(5, match.getTower_status_radiant());
                statement.setInt(6, match.getTower_status_dire());
                statement.setInt(7, match.getBarracks_status_radiant());
                statement.setInt(8, match.getBarracks_status_dire());
                statement.setInt(9, match.getCluster());
                statement.setInt(10, match.getFirst_blood_time());
                statement.setInt(11, match.getHuman_players());
                statement.setInt(12, match.getPositive_votes());
                statement.setInt(13, match.getNegative_votes());
                statement.setInt(14, match.getLobbyType().getId());
                statement.setInt(15, match.getGameMode().getId());
                statement.setLong(16, match.getLeague().getId());
                statement.execute();
                log.info(String.format("The entity has been saved [id: %d]", match.getMatch_id()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %d]", match.getMatch_id()));
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %d]", match.getMatch_id()));
            }
        }
    }

    @Override
    public Match read(Integer id) {

        String sql = "SELECT * FROM matches m INNER JOIN leagues l ON m.leagueid = l.id " +
                "INNER JOIN gameModes g ON m.game_mode=g.id " +
                "INNER JOIN lobbytypes lt ON m.id_lobby_type=lt.id WHERE match_id = " + id + " LIMIT 1;";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                long match_id = resultSet.getLong("match_id");
                Boolean radiant_win = resultSet.getBoolean("radiant_win");
                long duration = resultSet.getLong("duration");
                long start_time = resultSet.getLong("start_time");
                int tower_status_radiant = resultSet.getInt("tower_status_radiant");
                int tower_status_dire = resultSet.getInt("tower_status_dire");
                int barracks_status_radiant = resultSet.getInt("barracks_status_radiant");
                int barracks_status_dire = resultSet.getInt("barracks_status_dire");
                int cluster = resultSet.getInt("cluster");
                int first_blood_time = resultSet.getInt("first_blood_time");
                int human_players = resultSet.getInt("human_players");
                int positive_votes = resultSet.getInt("positive_votes");
                int negative_votes = resultSet.getInt("negative_votes");
                LobbyType lobbyType = new LobbyType(resultSet.getInt("lt.id"), resultSet.getString("lt.name"));
                GameMode gameMode = new GameMode(resultSet.getInt("g.id"), resultSet.getString("g.name"));
                League league = new League(resultSet.getLong("l.id"), resultSet.getString("l.name"),
                        resultSet.getString("l.description"), resultSet.getString("l.tournament_url"));
                return new Match(match_id, radiant_win, duration, start_time, tower_status_radiant, tower_status_dire,
                        barracks_status_radiant, barracks_status_dire, cluster, first_blood_time, human_players,
                        positive_votes, negative_votes, lobbyType, gameMode, league);
            }else {
                log.error(String.format("The gameMode [%d] has not found", id));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isMatchExist(long matchID){
        String sql = "SELECT * FROM matches WHERE match_id = " + matchID + " LIMIT 1;";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

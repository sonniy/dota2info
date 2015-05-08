package org.all.info.dao.jdbc;

import org.all.info.dao.LeagueDAO;
import org.all.info.model.match.League;
import org.all.info.util.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class LeagueDAOImpl implements LeagueDAO {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void save(League league) {
        if (isLeagueExist(league.getName())){
            log.error(String.format("The League [%s] already exists", league.getName()));
        } else {
            String saveSQL = "INSERT INTO leagues VALUES(? , ? , ? , ?)";
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(saveSQL)){
                statement.setLong(1, league.getId());
                statement.setString(2, league.getName());
                statement.setString(3, league.getDescription());
                statement.setString(4, league.getTournament_url());
                statement.execute();
                log.info(String.format("The entity has been saved [id: %d, name: %s]", league.getId(), league.getName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", league.getName()));
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", league.getName()));
            }
        }
    }

    @Override
    public League read(Long id) {
        long leagueID;
        String leagueName;
        String leagueDescription;
        String leagueURL;
        String sql = "SELECT * FROM leagues WHERE id = " + id + " LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                leagueID = resultSet.getInt("id");
                leagueName = resultSet.getString("name");
                leagueDescription = resultSet.getString("description");
                leagueURL = resultSet.getString("tournament_url");
                return new League(leagueID, leagueName, leagueDescription, leagueURL);
            }else {
                log.error(String.format("The league [%d] has not found", id));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public League read(String name) {
        long leagueID;
        String leagueName;
        String leagueDescription;
        String leagueURL;
        String sql = "SELECT * FROM leagues WHERE name = '" + name + "' LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                leagueID = resultSet.getInt("id");
                leagueName = resultSet.getString("name");
                leagueDescription = resultSet.getString("description");
                leagueURL = resultSet.getString("tournament_url");
                return new League(leagueID, leagueName, leagueDescription, leagueURL);
            }else {
                log.error(String.format("The league [%s] has not found", name));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isLeagueExist(String leagueName){
        String sql = "SELECT * FROM leagues WHERE name = '" + leagueName + "' LIMIT 1;";
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

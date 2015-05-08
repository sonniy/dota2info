package org.all.info.dao.jdbc;

import org.all.info.dao.LobbyTypeDAO;
import org.all.info.model.match.LobbyType;
import org.all.info.util.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class LobbyTypeDAOImpl implements LobbyTypeDAO {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void save(LobbyType lobbyType) {
        if (isLobbyTypeExist(lobbyType.getName())){
            log.error(String.format("The LobbyType [%s] already exists", lobbyType.getName()));
        } else {
            String saveSQL = "INSERT INTO lobbyTypes VALUES(? , ?)";
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(saveSQL)){
                statement.setInt(1, lobbyType.getId());
                statement.setString(2, lobbyType.getName());
                statement.execute();
                log.info(String.format("The entity has been saved [id: %d, name: %s]", lobbyType.getId(), lobbyType.getName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", lobbyType.getName()));
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", lobbyType.getName()));
            }
        }
    }

    @Override
    public LobbyType read(String name) {
        int lobbyTypeId;
        String lobbyTypeName;
        String sql = "SELECT * FROM lobbyTypes WHERE name = '" + name + "' LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                lobbyTypeId = resultSet.getInt("id");
                lobbyTypeName = resultSet.getString("name");
                return new LobbyType(lobbyTypeId, lobbyTypeName);
            }else {
                log.error(String.format("The lobbyType [%s] has not found", name));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LobbyType read(Integer id) {
        int lobbyTypeId;
        String lobbyTypeName;
        String sql = "SELECT * FROM lobbyTypes WHERE id = " + id + " LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                lobbyTypeId = resultSet.getInt("id");
                lobbyTypeName = resultSet.getString("name");
                return new LobbyType(lobbyTypeId, lobbyTypeName);
            }else {
                log.error(String.format("The lobbyType [id: %d] has not found", id));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isLobbyTypeExist(String lobbyTypeName){
        String sql = "SELECT * FROM lobbyTypes WHERE name = '" + lobbyTypeName + "' LIMIT 1;";
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

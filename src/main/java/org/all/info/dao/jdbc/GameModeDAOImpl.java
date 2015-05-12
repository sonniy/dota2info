package org.all.info.dao.jdbc;

import org.all.info.dao.GameModeDAO;
import org.all.info.model.match.GameMode;
import org.all.info.util.ConnectionFactory;

import java.sql.*;

public class GameModeDAOImpl extends  GeneralDAO implements GameModeDAO{

    @Override
    public void save(GameMode gameMode) {
        if (isExist(gameMode.getName(), "gameModes")){
            log.error(String.format("The LobbyType [%s] already exists", gameMode.getName()));
        } else {
            String saveSQL = "INSERT INTO gameModes VALUES(? , ?)";
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(saveSQL)){
                statement.setInt(1, gameMode.getId());
                statement.setString(2, gameMode.getName());
                statement.execute();
                log.info(String.format("The entity has been saved [id: %d, name: %s]", gameMode.getId(), gameMode.getName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", gameMode.getName()));
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", gameMode.getName()));
            }
        }
    }

    @Override
    public GameMode read(String name) {
        int gameModeId;
        String gameModeName;
        String sql = "SELECT * FROM gameModes WHERE name = '" + name + "' LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                gameModeId = resultSet.getInt("id");
                gameModeName = resultSet.getString("name");
                return new GameMode(gameModeId, gameModeName);
            }else {
                log.error(String.format("The gameMode [%s] has not found", name));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GameMode read(Integer id) {
        int gameModeId;
        String gameModeName;
        String sql = "SELECT * FROM gameModes WHERE id = " + id + " LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                gameModeId = resultSet.getInt("id");
                gameModeName = resultSet.getString("name");
                return new GameMode(gameModeId, gameModeName);
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
}

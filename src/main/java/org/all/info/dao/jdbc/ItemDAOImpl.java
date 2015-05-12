package org.all.info.dao.jdbc;

import org.all.info.dao.ItemDAO;
import org.all.info.model.player.Item;
import org.all.info.util.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ItemDAOImpl extends GeneralDAO implements ItemDAO {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void save(Item item) {
        if (isExist(item.getName(), "items")){
            log.error(String.format("The Hero [%s] already exists", item.getName()));
        } else {
            String saveSQL = "INSERT INTO items VALUES(?, ?, ?)";
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(saveSQL)){
                statement.setInt(1, item.getId());
                statement.setString(2, item.getName());
                statement.setString(3, item.getImg());
                statement.execute();
                log.info(String.format("The entity has been saved [id: %d, name: %s]", item.getId(), item.getName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", item.getName()));
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", item.getName()));
            }
        }
    }

    @Override
    public Item read(String name) {
        String sql = "SELECT * FROM items WHERE name = '" + name + "' LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                int itemId = resultSet.getInt("id");
                String itemName = resultSet.getString("name");
                String itemImg = resultSet.getString("img");
                return new Item(itemId, itemName, itemImg);
            }else {
                log.error(String.format("The item [%s] has not found", name));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Item read(Integer id) {
        String sql = "SELECT * FROM items WHERE id = " + id + " LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                int itemId = resultSet.getInt("id");
                String itemName = resultSet.getString("name");
                String itemImg = resultSet.getString("img");
                return new Item(itemId, itemName, itemImg);
            }else {
                log.error(String.format("The item [%d] has not found", id));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

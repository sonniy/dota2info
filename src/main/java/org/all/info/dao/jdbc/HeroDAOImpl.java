package org.all.info.dao.jdbc;

import org.all.info.dao.HeroDAO;
import org.all.info.model.player.Hero;
import org.all.info.util.ConnectionFactory;

import java.sql.*;

public class HeroDAOImpl extends GeneralDAO implements HeroDAO{

    @Override
    public void save(Hero hero) {
        if (isExist(hero.getName(), "heroes")){
            log.error(String.format("The Hero [%s] already exists", hero.getName()));
        } else {
            String saveSQL = "INSERT INTO heroes VALUES(?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(saveSQL)){
                statement.setLong(1, hero.getId());
                statement.setString(2, hero.getName());
                statement.setString(3, hero.getLocalized_name());
                statement.setString(4, hero.getSmallImg());
                statement.setString(5, hero.getLargeImg());
                statement.setString(6, hero.getFullVerticalImg());
                statement.setString(7, hero.getFullHorizontalImg());
                statement.execute();
                log.info(String.format("The entity has been saved [id: %d, name: %s]", hero.getId(), hero.getName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", hero.getName()));
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(String.format("Error during entity saving [name: %s]", hero.getName()));
            }
        }
    }

    @Override
    public Hero read(String name) {
        String sql = "SELECT * FROM heroes WHERE name = '" + name + "' LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                int heroId = resultSet.getInt("id");
                String heroName = resultSet.getString("name");
                String heroLocalizedName = resultSet.getString("localized_name");
                String heroSmallImg = resultSet.getString("small_img");
                String heroLargeImg = resultSet.getString("large_img");
                String heroFullVerticalImg = resultSet.getString("full_vertical_img");
                String heroFullHorizontalImg = resultSet.getString("full_horizontal_img");
                return new Hero(heroId, heroName, heroLocalizedName, heroSmallImg, heroLargeImg,
                        heroFullVerticalImg, heroFullHorizontalImg);
            }else {
                log.error(String.format("The hero [%s] has not found", name));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Hero read(Integer id) {
        String sql = "SELECT * FROM heroes WHERE id = " + id + " LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                int heroId = resultSet.getInt("id");
                String heroName = resultSet.getString("name");
                String heroLocalizedName = resultSet.getString("localized_name");
                String heroSmallImg = resultSet.getString("small_img");
                String heroLargeImg = resultSet.getString("large_img");
                String heroFullVerticalImg = resultSet.getString("full_vertical_img");
                String heroFullHorizontalImg = resultSet.getString("full_horizontal_img");
                return new Hero(heroId, heroName, heroLocalizedName, heroSmallImg, heroLargeImg,
                        heroFullVerticalImg, heroFullHorizontalImg);
            }else {
                log.error(String.format("The hero [id: %d] has not found", id));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

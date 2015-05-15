package org.all.info.dao.jdbc;

import org.all.info.dao.PlayerSlotDAO;
import org.all.info.model.player.PlayerSlot;
import org.all.info.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerSlotDAOImpl extends GeneralDAO implements PlayerSlotDAO {


    @Override
    public void save(PlayerSlot playerSlot) {

        String saveSQL = "INSERT INTO playerSlots(account_id,player_slot,match_id,hero,kills,deaths,assists, leaver_status , gold, last_hits, \n" +
                "                        denies, xp_per_min, gold_per_min, gold_spent, hero_damage, tower_damage, hero_healing, \n" +
                "                        level, item_0, item_1, item_2, item_3, item_4, item_5) " +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(saveSQL)){
            statement.setLong(1, playerSlot.getAccount_id());
            statement.setInt(2, playerSlot.getPlayer_slot());
            statement.setLong(3, playerSlot.getMatch_id());
            statement.setInt(4, playerSlot.getHero().getId());
            statement.setInt(5, playerSlot.getKills());
            statement.setInt(6, playerSlot.getDeaths());
            statement.setInt(7, playerSlot.getAssists());
            statement.setInt(8, playerSlot.getLeaver_status());
            statement.setInt(9, playerSlot.getGold());
            statement.setInt(10, playerSlot.getLast_hits());
            statement.setInt(11, playerSlot.getDenies());
            statement.setInt(12, playerSlot.getXp_per_min());
            statement.setInt(13, playerSlot.getGold_per_min());
            statement.setInt(14, playerSlot.getGold_spent());
            statement.setInt(15, playerSlot.getHero_damage());
            statement.setInt(16, playerSlot.getTower_damage());
            statement.setInt(17, playerSlot.getHero_healing());
            statement.setInt(18, playerSlot.getLevel());
            statement.setInt(19, playerSlot.getItem_0().getId());
            statement.setInt(20, playerSlot.getItem_1().getId());
            statement.setInt(21, playerSlot.getItem_2().getId());
            statement.setInt(22, playerSlot.getItem_3().getId());
            statement.setInt(23, playerSlot.getItem_4().getId());
            statement.setInt(24, playerSlot.getItem_5().getId());

            statement.execute();
            log.info(String.format("The entity has been saved [match_id: %d, account_id: %d]", playerSlot.getMatch_id(),
                    playerSlot.getAccount_id()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error(String.format("Error during entity saving [match_id: %d]", playerSlot.getMatch_id()));
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(String.format("Error during entity saving [match_id: %d]", playerSlot.getMatch_id()));
        }
    }
}

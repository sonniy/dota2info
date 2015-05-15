package org.all.info.service.player;

import org.all.info.dao.PlayerSlotDAO;
import org.all.info.model.player.PlayerSlot;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerSlotServiceImpl implements PlayerSlotService {

    @Autowired
    private PlayerSlotDAO playerSlotDAO;

    @Override
    public void save(PlayerSlot playerSlot) {
        playerSlotDAO.save(playerSlot);
    }
}

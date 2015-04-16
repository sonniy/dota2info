package org.all.info.dao.player;

import org.all.info.model.player.PlayerSlot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerSlotDAOImpl implements PlayerSlotDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public PlayerSlotDAOImpl() {
    }

    @Override
    public void save(PlayerSlot playerSlot) {
        Session session = sessionFactory.getCurrentSession();
        session.save(playerSlot);
    }
}

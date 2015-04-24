package org.all.info.dao.hibernate;

import org.all.info.dao.LobbyTypeDAO;
import org.all.info.model.match.LobbyType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LobbyTypeDAOImpl implements LobbyTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public LobbyTypeDAOImpl() {
    }

    @Override
    public void save(LobbyType lobbyType) {
        Session session = sessionFactory.getCurrentSession();
        session.save(lobbyType);
    }

    @Override
    public LobbyType read(String name) {
        Session session = sessionFactory.getCurrentSession();
        LobbyType lobbyType = (LobbyType) session.createCriteria(LobbyType.class)
                .add(Restrictions.eq("name", name))
                .setMaxResults(1).uniqueResult();
        return lobbyType;
    }

    @Override
    public LobbyType read(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        LobbyType lobbyType = (LobbyType) session.get(LobbyType.class, id);
        return lobbyType;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

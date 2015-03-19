package org.all.info.dao.match;

import org.all.info.model.match.GameMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameModeDAOImpl implements GameModeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public GameModeDAOImpl() {
    }

    @Override
    public void save(GameMode gameMode) {
        Session session = sessionFactory.getCurrentSession();
        session.save(gameMode);
    }

    @Override
    public GameMode read(String name) {
        Session session = sessionFactory.getCurrentSession();
        GameMode gameMode = (GameMode) session.createCriteria(GameMode.class)
                .add(Restrictions.eq("name", name))
                .setMaxResults(1).uniqueResult();
        return gameMode;
    }

    @Override
    public GameMode read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        GameMode gameMode = (GameMode) session.get(GameMode.class, id);
        return  gameMode;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

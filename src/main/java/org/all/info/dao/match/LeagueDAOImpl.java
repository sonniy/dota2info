package org.all.info.dao.match;

import org.all.info.model.match.League;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeagueDAOImpl implements LeagueDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public LeagueDAOImpl() {
    }

    @Override
    public void save(League league) {
        Session session = sessionFactory.getCurrentSession();
        session.save(league);
    }

    @Override
    public League read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        League league = (League) session.get(League.class, id);
        return league;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
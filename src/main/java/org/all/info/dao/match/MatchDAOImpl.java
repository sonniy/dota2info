package org.all.info.dao.match;

import org.all.info.model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MatchDAOImpl implements MatchDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public MatchDAOImpl() {
    }

    @Override
    public void saveMatch(Match match) {
        Session session = sessionFactory.getCurrentSession();
        session.save(match);
    }


    /* Getters and Setters */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

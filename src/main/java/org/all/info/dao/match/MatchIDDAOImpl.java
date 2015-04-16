package org.all.info.dao.match;

import org.all.info.model.match.MatchID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MatchIDDAOImpl implements MatchIDDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public MatchIDDAOImpl() {
    }

    @Override
    public void save(MatchID matchID) {
        Session session = sessionFactory.getCurrentSession();
        session.save(matchID);
    }

    @Override
    public MatchID read(Long matchID) {
        Session session = sessionFactory.getCurrentSession();
        MatchID mID = (MatchID) session.createCriteria(MatchID.class)
                .add(Restrictions.eq("match_id", matchID))
                .setMaxResults(1).uniqueResult();
        return mID;
    }

    /* Gives maximum match_seq_num */
    @Override
    public Long readLastMatchSeqNum() {
        Session session = sessionFactory.getCurrentSession();
        MatchID matchID = (MatchID) session.createCriteria(MatchID.class)
                .addOrder(Order.desc("match_seq_num"))
                .setMaxResults(1).uniqueResult();
        if (matchID != null){
            return matchID.getMatch_seq_num();
        } else{
            return Long.valueOf(240);
        }

    }

    /* Gives minimum match_id which wasn't parsed */
    @Override
    public Long readLastMatchID() {
        Session session = sessionFactory.getCurrentSession();
        MatchID matchID = (MatchID) session.createCriteria(MatchID.class)
                .add(Restrictions.eq("isParsed", false))
                .addOrder(Order.asc("match_id"))
                .setMaxResults(1).uniqueResult();
        if (matchID != null){
            return matchID.getMatch_id();
        } else{
            return Long.valueOf(496);
        }
    }

    @Override
    public void update(MatchID matchID) {
        Session session = sessionFactory.getCurrentSession();
        session.update(matchID);
    }

    @Override
    public Boolean isMatchExist(Long matchSeqNum) {
        Session session = sessionFactory.getCurrentSession();
        MatchID matchID = (MatchID) session.get(MatchID.class, matchSeqNum);
        return matchID != null;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

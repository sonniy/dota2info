package org.all.info.dao.matchID;

import org.all.info.model.MatchID;
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
    public void saveMatchID(MatchID matchID) {
        Session session = sessionFactory.getCurrentSession();
        session.save(matchID);
    }

    /* Gives maximum match_seq_num */
    @Override
    public Long readLastMatchSeqNum() {
        Session session = sessionFactory.getCurrentSession();
        MatchID matchID = (MatchID) session.createCriteria(MatchID.class)
                .addOrder(Order.desc("match_seq_num")).setMaxResults(1).uniqueResult();
        if (matchID != null){
            return matchID.getMatch_seq_num();
        } else{
            return Long.valueOf(240);
        }

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

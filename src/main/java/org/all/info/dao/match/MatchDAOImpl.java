package org.all.info.dao.match;

import org.all.info.model.*;
import org.all.info.model.match.GameMode;
import org.all.info.model.match.League;
import org.all.info.model.match.LobbyType;
import org.all.info.model.match.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MatchDAOImpl implements MatchDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public MatchDAOImpl() {
    }

    @Override
    public void save(Match match) {
        /* TODO погуглить насчет сохранения many-to-one */
        Session session = sessionFactory.getCurrentSession();
        League leagueDB = (League) session.get(League.class, match.getLeague().getId());
        GameMode gameModeDB = (GameMode) session.get(GameMode.class, match.getGameMode().getId());
        LobbyType lobbyTypeDB = (LobbyType) session.get(LobbyType.class, match.getLobbyType().getId());

        leagueDB.getMatches().add(match);
        gameModeDB.getMatches().add(match);
        lobbyTypeDB.getMatches().add(match);

        match.setLeague(leagueDB);
        match.setGameMode(gameModeDB);
        match.setLobbyType(lobbyTypeDB);

        session.save(match);
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
            return Long.valueOf(240);
        }
    }


    /* Getters and Setters */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

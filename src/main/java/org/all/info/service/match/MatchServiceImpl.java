package org.all.info.service.match;

import org.all.info.dao.match.MatchDAO;
import org.all.info.model.Match;
import org.all.info.model.MatchID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MatchServiceImpl implements MatchService{

    @Autowired
    private MatchDAO matchDAO;

    public MatchServiceImpl() {
    }

    @Override
    public void saveMatch(Match match) {
        matchDAO.saveMatch(match);
    }

    @Override
    public Long readLastMatchID() {
        return matchDAO.readLastMatchID();
    }

    /* Getters and Setters */
    public MatchDAO getMatchDAO() {
        return matchDAO;
    }

    public void setMatchDAO(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

}

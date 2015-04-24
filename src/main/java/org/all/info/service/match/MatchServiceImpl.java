package org.all.info.service.match;

import org.all.info.dao.MatchDAO;
import org.all.info.model.match.Match;
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
        matchDAO.save(match);
    }

    /* Getters and Setters */
    public MatchDAO getMatchDAO() {
        return matchDAO;
    }

    public void setMatchDAO(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

}

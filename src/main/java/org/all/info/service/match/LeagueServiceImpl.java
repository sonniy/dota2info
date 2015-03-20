package org.all.info.service.match;

import org.all.info.dao.match.LeagueDAO;
import org.all.info.model.match.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LeagueServiceImpl implements LeagueService{

    @Autowired
    private LeagueDAO leagueDAO;

    public LeagueServiceImpl() {
    }

    @Override
    public void save(League league) {
        leagueDAO.save(league);
    }

    @Override
    public League read(Long id) {
        return leagueDAO.read(id);
    }

    public LeagueDAO getLeagueDAO() {
        return leagueDAO;
    }

    public void setLeagueDAO(LeagueDAO leagueDAO) {
        this.leagueDAO = leagueDAO;
    }
}


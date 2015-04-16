package org.all.info.service.match;

import org.all.info.dao.match.MatchIDDAO;
import org.all.info.model.match.MatchID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MatchIDServiceImpl implements MatchIDService {

    @Autowired
    private MatchIDDAO matchIDDAO;

    @Override
    public void saveMatchID(MatchID matchID) {
        matchIDDAO.save(matchID);
    }

    @Override
    public MatchID read(Long matchID) {
        return matchIDDAO.read(matchID);
    }

    @Override
    public Long readLastMatchSeqNum() {
        return matchIDDAO.readLastMatchSeqNum();
    }

    @Override
    public Long readLastMatchID() {
        return matchIDDAO.readLastMatchID();
    }

    @Override
    public void update(MatchID matchID) {
        matchIDDAO.update(matchID);
    }

    @Override
    public Boolean isMatchExist(Long matchSeqNum) {
        return matchIDDAO.isMatchExist(matchSeqNum);
    }
}

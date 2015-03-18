package org.all.info.service.matchID;

import org.all.info.dao.matchID.MatchIDDAO;
import org.all.info.model.MatchID;
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
        matchIDDAO.saveMatchID(matchID);
    }

    @Override
    public Long readLastMatchSeqNum() {
        return matchIDDAO.readLastMatchSeqNum();
    }

    @Override
    public Boolean isMatchExist(Long matchSeqNum) {
        return matchIDDAO.isMatchExist(matchSeqNum);
    }
}

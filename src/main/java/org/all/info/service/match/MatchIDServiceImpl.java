package org.all.info.service.match;

import org.all.info.dao.MatchIDDAO;
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
        try {
            matchIDDAO.save(matchID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MatchID read(Long matchID) {
        try {
            return matchIDDAO.read(matchID);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        return matchIDDAO.isMatchSeqNumExist(matchSeqNum);
    }
}

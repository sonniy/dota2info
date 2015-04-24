package org.all.info.dao;

import org.all.info.model.match.MatchID;

public interface MatchIDDAO {

    public void save(MatchID matchID) throws Exception;

    public MatchID read(Long matchID) throws Exception;

    public Long readLastMatchSeqNum();

    public Long readLastMatchID();

    public void update(MatchID matchID);

    public Boolean isMatchSeqNumExist(Long matchSeqNum);

}

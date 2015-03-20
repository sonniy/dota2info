package org.all.info.dao.matchID;

import org.all.info.model.MatchID;

public interface MatchIDDAO {

    public void save(MatchID matchID);

    public MatchID read(Long matchID);

    public Long readLastMatchSeqNum();

    public Long readLastMatchID();

    public void update(MatchID matchID);

    public Boolean isMatchExist(Long matchSeqNum);

}

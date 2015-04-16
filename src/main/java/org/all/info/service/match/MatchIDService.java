package org.all.info.service.match;

import org.all.info.model.match.MatchID;

public interface MatchIDService {

    public void saveMatchID(MatchID matchID);

    public MatchID read(Long matchID);

    public Long readLastMatchSeqNum();

    public Long readLastMatchID();

    public void update(MatchID matchID);

    public Boolean isMatchExist(Long matchSeqNum);
}

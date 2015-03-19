package org.all.info.service.matchID;

import org.all.info.model.MatchID;

/**
 * Created by yuriy.gorbylev on 18.03.2015.
 */
public interface MatchIDService {

    public void saveMatchID(MatchID matchID);

    public MatchID read(Long matchID);

    public Long readLastMatchSeqNum();

    public Long readLastMatchID();

    public void update(MatchID matchID);

    public Boolean isMatchExist(Long matchSeqNum);
}

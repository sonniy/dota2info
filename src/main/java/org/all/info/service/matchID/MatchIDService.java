package org.all.info.service.matchID;

import org.all.info.model.MatchID;

/**
 * Created by yuriy.gorbylev on 18.03.2015.
 */
public interface MatchIDService {

    public void saveMatchID(MatchID matchID);

    public Long readLastMatchSeqNum();

    public Boolean isMatchExist(Long matchSeqNum);
}

package org.all.info.dao.matchID;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.all.info.model.MatchID;

/**
 * Created by yuriy.gorbylev on 18.03.2015.
 */
public interface MatchIDDAO {

    public void saveMatchID(MatchID matchID);

    public Long readLastMatchSeqNum();

    public Boolean isMatchExist(Long matchSeqNum);

}

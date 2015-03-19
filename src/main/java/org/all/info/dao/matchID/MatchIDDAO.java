package org.all.info.dao.matchID;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.all.info.model.MatchID;

/**
 * Created by yuriy.gorbylev on 18.03.2015.
 */
public interface MatchIDDAO {

    public void save(MatchID matchID);

    public MatchID read(Long matchID);

    public Long readLastMatchSeqNum();

    public Long readLastMatchID();

    public void update(MatchID matchID);

    public Boolean isMatchExist(Long matchSeqNum);

}

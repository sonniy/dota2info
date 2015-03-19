package org.all.info.dao.match;

import org.all.info.model.match.GameMode;
import org.all.info.model.match.League;
import org.all.info.model.match.LobbyType;
import org.all.info.model.match.Match;

/**
 * Created by yuriy.gorbylev on 18.03.2015.
 */
public interface MatchDAO {

    public void save(Match match);

    public Long readLastMatchID();

}

package org.all.info.service.match;

import org.all.info.model.match.GameMode;
import org.all.info.model.match.League;
import org.all.info.model.match.LobbyType;
import org.all.info.model.match.Match;

/**
 * Created by yuriy.gorbylev on 18.03.2015.
 */
public interface MatchService {

    public void saveMatch(Match match);

    public Long readLastMatchID();

    public GameMode getGameMode(Long id);

    public LobbyType getLobbyType(Long id);
}

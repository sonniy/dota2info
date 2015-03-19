package org.all.info.dao.match;

import org.all.info.model.match.GameMode;
import org.all.info.model.match.LobbyType;

/**
 * Created by yuriy.gorbylev on 19.03.2015.
 */
public interface LobbyTypeDAO {

    public void save(LobbyType lobbyType);

    public LobbyType read(String name);

    public LobbyType read(Long id);

}

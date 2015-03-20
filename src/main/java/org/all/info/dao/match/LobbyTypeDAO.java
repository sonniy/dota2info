package org.all.info.dao.match;

import org.all.info.model.match.LobbyType;

public interface LobbyTypeDAO {

    public void save(LobbyType lobbyType);

    public LobbyType read(String name);

    public LobbyType read(Integer id);

}

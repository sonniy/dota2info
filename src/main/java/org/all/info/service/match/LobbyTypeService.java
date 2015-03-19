package org.all.info.service.match;

import org.all.info.model.match.LobbyType;

public interface LobbyTypeService {

    public void save(LobbyType lobbyType);

    public LobbyType read(String name);

    public LobbyType read(Integer id);
}

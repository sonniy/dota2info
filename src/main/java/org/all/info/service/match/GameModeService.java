package org.all.info.service.match;

import org.all.info.model.match.GameMode;

public interface GameModeService {

    public void save(GameMode gameMode);

    public GameMode read(String name);

    public GameMode read(Integer id);

}

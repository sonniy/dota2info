package org.all.info.dao.match;

import org.all.info.model.match.GameMode;

public interface GameModeDAO {

    public void save(GameMode gameMode);

    public GameMode read(String name);

    public GameMode read(Integer id);

}

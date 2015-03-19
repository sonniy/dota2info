package org.all.info.service.match;

import org.all.info.dao.match.GameModeDAO;
import org.all.info.model.match.GameMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class GameModeServiceImpl implements GameModeService {

    @Autowired
    private GameModeDAO gameModeDAO;

    public GameModeServiceImpl() {
    }

    @Override
    public void save(GameMode gameMode) {
        gameModeDAO.save(gameMode);
    }

    @Override
    public GameMode read(String name) {
        return gameModeDAO.read(name);
    }

    @Override
    public GameMode read(Long id) {
        return gameModeDAO.read(id);
    }

    public GameModeDAO getGameModeDAO() {
        return gameModeDAO;
    }

    public void setGameModeDAO(GameModeDAO gameModeDAO) {
        this.gameModeDAO = gameModeDAO;
    }
}

package org.all.info.service.match;


import org.all.info.dao.match.LobbyTypeDAO;
import org.all.info.model.match.LobbyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LobbyTypeServiceImpl implements LobbyTypeService{

    @Autowired
    private LobbyTypeDAO lobbyTypeDAO;

    public LobbyTypeServiceImpl() {
    }

    @Override
    public void save(LobbyType lobbyType) {
        lobbyTypeDAO.save(lobbyType);
    }

    @Override
    public LobbyType read(String name) {
        return lobbyTypeDAO.read(name);
    }

    @Override
    public LobbyType read(Integer id) {
        return lobbyTypeDAO.read(id);
    }

    public LobbyTypeDAO getLobbyTypeDAO() {
        return lobbyTypeDAO;
    }

    public void setLobbyTypeDAO(LobbyTypeDAO lobbyTypeDAO) {
        this.lobbyTypeDAO = lobbyTypeDAO;
    }
}

package org.all.info.service.player;

import org.all.info.dao.player.HeroDAO;
import org.all.info.model.player.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HeroServiceImpl implements HeroService{

    @Autowired
    private HeroDAO heroDAO;

    public HeroServiceImpl() {
    }

    @Override
    public void save(Hero hero) {
        heroDAO.save(hero);
    }

    @Override
    public Hero read(String name) {
        return heroDAO.read(name);
    }

    @Override
    public Hero read(Integer id) {
        return heroDAO.read(id);
    }

    public HeroDAO getHeroDAO() {
        return heroDAO;
    }

    public void setHeroDAO(HeroDAO heroDAO) {
        this.heroDAO = heroDAO;
    }
}

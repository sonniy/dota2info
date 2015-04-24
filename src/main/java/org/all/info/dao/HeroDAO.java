package org.all.info.dao;

import org.all.info.model.player.Hero;

public interface HeroDAO {

    public void save(Hero hero);

    public Hero read(String name);

    public Hero read(Integer id);
}

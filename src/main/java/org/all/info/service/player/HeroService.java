package org.all.info.service.player;

import org.all.info.model.player.Hero;

public interface HeroService {

    public void save(Hero hero);

    public Hero read(String name);

    public Hero read(Integer id);
}

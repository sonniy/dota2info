package org.all.info.dao;

import org.all.info.model.match.League;

public interface LeagueDAO {

    public void save(League league);

    public League read(Long id);

    public League read(String name);
}

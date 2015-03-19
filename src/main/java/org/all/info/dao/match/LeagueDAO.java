package org.all.info.dao.match;

import org.all.info.model.match.League;

public interface LeagueDAO {

    public void save(League league);

    public League read(Long id);
}

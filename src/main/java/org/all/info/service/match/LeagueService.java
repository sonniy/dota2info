package org.all.info.service.match;

import org.all.info.model.match.League;

public interface LeagueService {

    public void save(League league);

    public League read(Long id);
}

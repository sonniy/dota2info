package org.all.info.dao;

import org.all.info.model.match.Match;

public interface MatchDAO {

    public void save(Match match);

    public Match read(Integer id);


}

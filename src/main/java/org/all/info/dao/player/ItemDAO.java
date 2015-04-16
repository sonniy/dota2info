package org.all.info.dao.player;

import org.all.info.model.player.Item;

public interface ItemDAO {

    public void save(Item item);

    public Item read(String name);

    public Item read(Integer id);
}

package org.all.info.service.player;


import org.all.info.model.player.Item;

public interface ItemService {

    public void save(Item item);

    public Item read(String name);

    public Item read(Integer id);
}

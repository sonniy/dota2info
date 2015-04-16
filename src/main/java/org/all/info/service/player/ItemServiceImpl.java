package org.all.info.service.player;

import org.all.info.dao.player.ItemDAO;
import org.all.info.model.player.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    public ItemServiceImpl() {
    }

    @Override
    public void save(Item item) {
        itemDAO.save(item);
    }

    @Override
    public Item read(String name) {
        return itemDAO.read(name);
    }

    @Override
    public Item read(Integer id) {
        return itemDAO.read(id);
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }
}

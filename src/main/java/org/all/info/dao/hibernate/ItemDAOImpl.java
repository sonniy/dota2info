package org.all.info.dao.hibernate;

import org.all.info.dao.ItemDAO;
import org.all.info.model.player.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public ItemDAOImpl() {
    }

    @Override
    public void save(Item item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    @Override
    public Item read(String name) {
        Session session = sessionFactory.getCurrentSession();
        Item item = (Item) session.createCriteria(Item.class)
                .add(Restrictions.eq("name", name))
                .setMaxResults(1).uniqueResult();
        return item;
    }

    @Override
    public Item read(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Item item = (Item) session.get(Item.class, id);
        return item;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

package org.all.info.dao.player;

import org.all.info.dao.player.HeroDAO;
import org.all.info.model.player.Hero;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HeroDAOImpl implements HeroDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public HeroDAOImpl() {
    }

    @Override
    public void save(Hero hero) {
        Session session = sessionFactory.getCurrentSession();
        session.save(hero);
    }

    @Override
    public Hero read(String name) {
        Session session = sessionFactory.getCurrentSession();
        Hero hero = (Hero) session.createCriteria(Hero.class)
                .add(Restrictions.eq("name", name))
                .setMaxResults(1).uniqueResult();
        return hero;
    }

    @Override
    public Hero read(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Hero hero = (Hero) session.get(Hero.class, id);
        return hero;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

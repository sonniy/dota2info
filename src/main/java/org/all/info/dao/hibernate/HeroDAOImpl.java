package org.all.info.dao.hibernate;

import org.all.info.dao.HeroDAO;
import org.all.info.model.player.Hero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HeroDAOImpl implements HeroDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private final Logger log = LogManager.getLogger(this.getClass());

    public HeroDAOImpl() {
    }

    @Override
    public void save(Hero hero) {
        Session session = sessionFactory.getCurrentSession();
        session.save(hero);
        log.info(String.format("The hero [id: %d, name: %s] has been saved.", hero.getId(), hero.getName()));
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

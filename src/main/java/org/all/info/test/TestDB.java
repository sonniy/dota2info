package org.all.info.test;

import org.all.info.dao.MatchDAO;
import org.all.info.dao.jdbc.MatchDAOImpl;

public class TestDB {

    public static void main(String[] args) {

        MatchDAO m = new MatchDAOImpl();

        System.out.println(m.read(496));
    }
}

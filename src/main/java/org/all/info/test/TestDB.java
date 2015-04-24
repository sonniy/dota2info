package org.all.info.test;

import org.all.info.dao.jdbc.MatchIDDAOImpl;
import org.all.info.model.match.MatchID;

public class TestDB {

    public static void main(String[] args) {

        MatchIDDAOImpl m = new MatchIDDAOImpl();

        MatchID matchID = new MatchID(3785L, 4748L, false);

        try {
            m.update(new MatchID(3785L, 4748L, true));
            System.out.println(m.read(4748L));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

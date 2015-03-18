package org.all.info.parser;

import org.all.info.service.match.MatchService;
import org.all.info.util.SpringUtil;


public class MatchParser implements Runnable {


    private MatchService matchService = (MatchService) SpringUtil.getApplicationContext().getBean("matchService");

    @Override
    public void run() {



    }
}

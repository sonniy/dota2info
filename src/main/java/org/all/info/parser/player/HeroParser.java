package org.all.info.parser.player;


import org.all.info.model.player.Hero;
import org.all.info.service.player.HeroService;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class HeroParser implements Runnable{

    private static Logger log = LogManager.getLogger(HeroParser.class);

    private HeroService heroService = (HeroService) SpringUtil.getApplicationContext().getBean("heroService");

    private static final String IMG_TEMP = "http://cdn.dota2.com/apps/dota2/images/heroes/";

    private String key = "0B9F3A6A9759528C543F28540C831C4A";

    private String urlTool = "&language=en_us";

    private String url = "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?key=" + key + urlTool;


    @Override
    public void run() {

        JSONObject root = HTTPClientUtil.getPageContent(url);
        JSONObject result = (JSONObject) root.get("result");
        JSONArray heroes = (JSONArray) result.get("heroes");

        long start = System.currentTimeMillis();

        for (int i = 0; i < heroes.size(); i++) {
            JSONObject JSONHero = (JSONObject) heroes.get(i);
            String id = String.valueOf((Long) JSONHero.get("id"));
            String name = (String) JSONHero.get("name");
            String localized_name = (String) JSONHero.get("localized_name");
            String smallImg = IMG_TEMP + name.substring(14) + "_sb.png";
            String largeImg = IMG_TEMP + name.substring(14) + "_lg.png";
            String fullHorizontalImg = IMG_TEMP + name.substring(14) + "_full.png";
            String fullVerticalImg = IMG_TEMP + name.substring(14) + "_vert.jpg";

            Hero hero = new Hero(Integer.valueOf(id), name, localized_name, smallImg, largeImg, fullVerticalImg, fullHorizontalImg);
            heroService.save(hero);
        }

        long finish = System.currentTimeMillis();
        long time = finish - start;
        System.out.println(time + " ms");
    }
}

package org.all.info.parser.player;

import org.all.info.model.player.Hero;
import org.all.info.model.player.Item;
import org.all.info.service.match.MatchIDService;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PlayerSlotParser implements Runnable {

    private MatchIDService matchIDService = (MatchIDService) SpringUtil.getApplicationContext().getBean("matchIDService");

    private String key = "0B9F3A6A9759528C543F28540C831C4A";

    private String tool = "&match_id=";

    private final String URL = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=" + key + tool;

    @Override
    public void run() {

        long matchID = matchIDService.readLastMatchID();

        JSONObject root = HTTPClientUtil.getPageContent(URL);
        JSONObject result = (JSONObject) root.get("result");
        JSONArray playerSlots = (JSONArray) result.get("players");

        long start = System.currentTimeMillis();

        for (int i = 0; i < playerSlots.size(); i++) {
            JSONObject JSONPlayerSlot = (JSONObject) playerSlots.get(i);

            Long account_id = (Long) JSONPlayerSlot.get("account_id");
            String player_slot = String.valueOf((Long)JSONPlayerSlot.get("player_slot"));
            Long match_id =
            Hero hero;
            Integer kills;
            Integer deaths;
            Integer assists;
            String leaver_status;
            Integer gold;
            Integer last_hits;
            Integer denies;
            Integer xp_per_min;
            Integer gold_per_min;
            Integer gold_spent;
            Integer hero_damage;
            Integer tower_damage;
            Integer hero_healing;
            Integer level;
            Item item_0;
            Item item_1;
            Item item_2;
            Item item_3;
            Item item_4;
            Item item_5;


        }

        long finish = System.currentTimeMillis();
        long time = finish - start;
        System.out.println(time + " ms");
    }
}

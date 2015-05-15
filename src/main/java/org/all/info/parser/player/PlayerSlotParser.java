package org.all.info.parser.player;

import org.all.info.model.player.Hero;
import org.all.info.model.player.Item;
import org.all.info.model.player.PlayerSlot;
import org.all.info.service.match.MatchIDService;
import org.all.info.service.player.HeroService;
import org.all.info.service.player.ItemService;
import org.all.info.service.player.PlayerSlotService;
import org.all.info.util.GeneralUtils;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PlayerSlotParser implements Runnable {

    private MatchIDService matchIDService = (MatchIDService) SpringUtil.getApplicationContext().getBean("matchIDService");
    private HeroService heroService = (HeroService) SpringUtil.getApplicationContext().getBean("heroService");
    private ItemService itemService = (ItemService) SpringUtil.getApplicationContext().getBean("itemService");
    private PlayerSlotService playerSlotService = (PlayerSlotService) SpringUtil.getApplicationContext().getBean("playerSlotService");

    private String key = "0B9F3A6A9759528C543F28540C831C4A";

    private String tool = "&match_id=";

    private final String URL = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=" + key + tool;

    @Override
    public void run() {

        long matchID = matchIDService.readLastMatchID();

        JSONObject root = HTTPClientUtil.getPageContent(URL+matchID);
        JSONObject result = (JSONObject) root.get("result");
        JSONArray playerSlots = (JSONArray) result.get("players");

        if (playerSlots != null) {

            for (int i = 0; i < playerSlots.size(); i++) {
                JSONObject JSONPlayerSlot = (JSONObject) playerSlots.get(i);

                Long account_id = (Long) JSONPlayerSlot.get("account_id");
                Integer player_slot = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("player_slot"));
                Long match_id = (Long) result.get("match_id");
                Hero hero = heroService.read(GeneralUtils.convertLongToInt(JSONPlayerSlot.get("hero_id")));
                Integer kills = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("kills"));
                Integer deaths = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("deaths"));
                Integer assists = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("assists"));
                Integer leaver_status;
                if (JSONPlayerSlot.get("leaver_status") != null){
                    leaver_status = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("leaver_status"));
                } else{
                    leaver_status = 0;
                }
                Integer gold = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("gold"));
                Integer last_hits = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("last_hits"));
                Integer denies = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("denies"));
                Integer xp_per_min = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("xp_per_min"));
                Integer gold_per_min = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("gold_per_min"));
                Integer gold_spent = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("gold_spent"));
                Integer hero_damage = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("hero_damage"));
                Integer tower_damage = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("tower_damage"));
                Integer hero_healing = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("hero_healing"));
                Integer level = GeneralUtils.convertLongToInt(JSONPlayerSlot.get("level"));
                Item item_0 = itemService.read(GeneralUtils.convertLongToInt(JSONPlayerSlot.get("item_0")));
                Item item_1 = itemService.read(GeneralUtils.convertLongToInt(JSONPlayerSlot.get("item_1")));
                Item item_2 = itemService.read(GeneralUtils.convertLongToInt(JSONPlayerSlot.get("item_2")));
                Item item_3 = itemService.read(GeneralUtils.convertLongToInt(JSONPlayerSlot.get("item_3")));
                Item item_4 = itemService.read(GeneralUtils.convertLongToInt(JSONPlayerSlot.get("item_4")));
                Item item_5 = itemService.read(GeneralUtils.convertLongToInt(JSONPlayerSlot.get("item_5")));

                PlayerSlot playerSlot = new PlayerSlot(1L, account_id, player_slot, match_id, hero, kills, deaths, assists,
                        leaver_status, gold, last_hits, denies, xp_per_min, gold_per_min, gold_spent, hero_damage, tower_damage,
                        hero_healing, level, item_0, item_1, item_2, item_3, item_4, item_5);

                playerSlotService.save(playerSlot);

            }
        } else{
            System.out.println("THE ARRAY IS EMPTY");
        }
    }
}

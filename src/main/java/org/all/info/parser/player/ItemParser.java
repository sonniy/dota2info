package org.all.info.parser.player;


import org.all.info.model.player.Item;
import org.all.info.service.player.HeroService;
import org.all.info.service.player.ItemService;
import org.all.info.util.HTTPClientUtil;
import org.all.info.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ItemParser implements Runnable{

    private static Logger log = LogManager.getLogger(ItemParser.class);

    private ItemService itemService =  (ItemService) SpringUtil.getApplicationContext().getBean("itemService");

    private static final String JSON_PATH = "E:\\workspace\\dota2all-info\\src\\main\\resources\\data\\items.json";

    private static final String IMG_TEMP = "http://cdn.dota2.com/apps/dota2/images/items/";


    @Override
    public void run() {


        try {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(new FileReader(new File(JSON_PATH)));
            JSONArray items = (JSONArray) root.get("items");
            for (int i = 0; i < items.size(); i++) {
                JSONObject JSONItem = (JSONObject) items.get(i);
                String name = (String) JSONItem.get("name");
                String img = IMG_TEMP + name + "_lg.png";

                Item item = new Item(name, img);
                itemService.save(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

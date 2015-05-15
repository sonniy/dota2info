package org.all.info.model.player;


import javax.persistence.*;

public class PlayerSlot {

    @Id
    @Column(name = "id")
    private Long id;
    private Long account_id;
    private Integer player_slot;
    private Long match_id;
    @ManyToOne()
    @JoinColumn(name = "hero_id")
    private Hero hero;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer leaver_status;
    private Integer gold;
    private Integer last_hits;
    private Integer denies;
    private Integer xp_per_min;
    private Integer gold_per_min;
    private Integer gold_spent;
    private Integer hero_damage;
    private Integer tower_damage;
    private Integer hero_healing;
    private Integer level;
    private Item item_0;
    private Item item_1;
    private Item item_2;
    private Item item_3;
    private Item item_4;
    private Item item_5;

    public PlayerSlot(Long id, Long account_id, Integer player_slot, Long match_id,
                      Hero hero, Integer kills, Integer deaths, Integer assists, Integer leaver_status, Integer gold,
                      Integer last_hits, Integer denies, Integer xp_per_min, Integer gold_per_min, Integer gold_spent,
                      Integer hero_damage, Integer tower_damage, Integer hero_healing, Integer level,
                      Item item_0, Item item_1, Item item_2, Item item_3, Item item_4, Item item_5) {
        this.id = id;
        this.account_id = account_id;
        this.player_slot = player_slot;
        this.match_id = match_id;
        this.hero = hero;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.leaver_status = leaver_status;
        this.gold = gold;
        this.last_hits = last_hits;
        this.denies = denies;
        this.xp_per_min = xp_per_min;
        this.gold_per_min = gold_per_min;
        this.gold_spent = gold_spent;
        this.hero_damage = hero_damage;
        this.tower_damage = tower_damage;
        this.hero_healing = hero_healing;
        this.level = level;
        this.item_0 = item_0;
        this.item_1 = item_1;
        this.item_2 = item_2;
        this.item_3 = item_3;
        this.item_4 = item_4;
        this.item_5 = item_5;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Integer getPlayer_slot() {
        return player_slot;
    }

    public void setPlayer_slot(Integer player_slot) {
        this.player_slot = player_slot;
    }

    public Long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getLeaver_status() {
        return leaver_status;
    }

    public void setLeaver_status(Integer leaver_status) {
        this.leaver_status = leaver_status;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getLast_hits() {
        return last_hits;
    }

    public void setLast_hits(Integer last_hits) {
        this.last_hits = last_hits;
    }

    public Integer getDenies() {
        return denies;
    }

    public void setDenies(Integer denies) {
        this.denies = denies;
    }

    public Integer getXp_per_min() {
        return xp_per_min;
    }

    public void setXp_per_min(Integer xp_per_min) {
        this.xp_per_min = xp_per_min;
    }

    public Integer getGold_per_min() {
        return gold_per_min;
    }

    public void setGold_per_min(Integer gold_per_min) {
        this.gold_per_min = gold_per_min;
    }

    public Integer getGold_spent() {
        return gold_spent;
    }

    public void setGold_spent(Integer gold_spent) {
        this.gold_spent = gold_spent;
    }

    public Integer getHero_damage() {
        return hero_damage;
    }

    public void setHero_damage(Integer hero_damage) {
        this.hero_damage = hero_damage;
    }

    public Integer getTower_damage() {
        return tower_damage;
    }

    public void setTower_damage(Integer tower_damage) {
        this.tower_damage = tower_damage;
    }

    public Integer getHero_healing() {
        return hero_healing;
    }

    public void setHero_healing(Integer hero_healing) {
        this.hero_healing = hero_healing;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Item getItem_0() {
        return item_0;
    }

    public void setItem_0(Item item_0) {
        this.item_0 = item_0;
    }

    public Item getItem_1() {
        return item_1;
    }

    public void setItem_1(Item item_1) {
        this.item_1 = item_1;
    }

    public Item getItem_2() {
        return item_2;
    }

    public void setItem_2(Item item_2) {
        this.item_2 = item_2;
    }

    public Item getItem_3() {
        return item_3;
    }

    public void setItem_3(Item item_3) {
        this.item_3 = item_3;
    }

    public Item getItem_4() {
        return item_4;
    }

    public void setItem_4(Item item_4) {
        this.item_4 = item_4;
    }

    public Item getItem_5() {
        return item_5;
    }

    public void setItem_5(Item item_5) {
        this.item_5 = item_5;
    }

    @Override
    public String toString() {
        return "PlayerSlot{" +
                "id=" + id +
                ", account_id=" + account_id +
                ", player_slot=" + player_slot +
                ", match_id=" + match_id +
                ", hero=" + hero +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", leaver_status=" + leaver_status +
                ", gold=" + gold +
                ", last_hits=" + last_hits +
                ", denies=" + denies +
                ", xp_per_min=" + xp_per_min +
                ", gold_per_min=" + gold_per_min +
                ", gold_spent=" + gold_spent +
                ", hero_damage=" + hero_damage +
                ", tower_damage=" + tower_damage +
                ", hero_healing=" + hero_healing +
                ", level=" + level +
                ", item_0=" + item_0 +
                ", item_1=" + item_1 +
                ", item_2=" + item_2 +
                ", item_3=" + item_3 +
                ", item_4=" + item_4 +
                ", item_5=" + item_5 +
                '}';
    }
}

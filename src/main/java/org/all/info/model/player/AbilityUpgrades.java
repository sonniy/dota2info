package org.all.info.model.player;

import javax.persistence.Entity;

@Entity
public class AbilityUpgrades {

    private Long id;
    private Long time;
    private Integer level;
    private PlayerSlot playerSlot;
    private Ability ability;

    public AbilityUpgrades(Long time, Integer level, PlayerSlot playerSlot, Ability ability) {
        this.time = time;
        this.level = level;
        this.playerSlot = playerSlot;
        this.ability = ability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public PlayerSlot getPlayerSlot() {
        return playerSlot;
    }

    public void setPlayerSlot(PlayerSlot playerSlot) {
        this.playerSlot = playerSlot;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}

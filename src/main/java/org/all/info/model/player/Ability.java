package org.all.info.model.player;

import javax.persistence.Entity;

@Entity
public class Ability {

    private Long id;
    private String name;
    private String desc;

    public Ability(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

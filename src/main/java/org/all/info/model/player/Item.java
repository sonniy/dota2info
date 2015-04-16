package org.all.info.model.player;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "id")
    private Long id;
    private String name;
    private String img;
    @OneToMany(mappedBy = "item_0", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PlayerSlot> playerSlots;

    public Item(String name, String img) {
        this.name = name;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

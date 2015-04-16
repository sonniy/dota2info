package org.all.info.model.player;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @Column(name = "id")
    private Long id;
    private String name;
    private String localized_name;
    private String smallImg;
    private String largeImg;
    private String fullVerticalImg;
    private String fullHorizontalImg;
    @OneToMany(mappedBy = "hero", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PlayerSlot> playerSlots;

    public Hero(String name, String localized_name, String smallImg, String largeImg, String fullVerticalImg, String fullHorizontalImg) {
        this.name = name;
        this.localized_name = localized_name;
        this.smallImg = smallImg;
        this.largeImg = largeImg;
        this.fullVerticalImg = fullVerticalImg;
        this.fullHorizontalImg = fullHorizontalImg;
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

    public String getLocalized_name() {
        return localized_name;
    }

    public void setLocalized_name(String localized_name) {
        this.localized_name = localized_name;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getLargeImg() {
        return largeImg;
    }

    public void setLargeImg(String largeImg) {
        this.largeImg = largeImg;
    }

    public String getFullVerticalImg() {
        return fullVerticalImg;
    }

    public void setFullVerticalImg(String fullVerticalImg) {
        this.fullVerticalImg = fullVerticalImg;
    }

    public String getFullHorizontalImg() {
        return fullHorizontalImg;
    }

    public void setFullHorizontalImg(String fullHorizontalImg) {
        this.fullHorizontalImg = fullHorizontalImg;
    }
}

package org.all.info.model.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @Column(name = "id")
    private Integer id;
    private String name;
    private String localized_name;
    @Column(name = "small_img")
    private String smallImg;
    @Column(name = "large_img")
    private String largeImg;
    @Column(name = "full_vertical_img")
    private String fullVerticalImg;
    @Column(name = "full_horizontal_img")
    private String fullHorizontalImg;

    public Hero(String name, String localized_name, String smallImg, String largeImg, String fullVerticalImg, String fullHorizontalImg) {
        this.name = name;
        this.localized_name = localized_name;
        this.smallImg = smallImg;
        this.largeImg = largeImg;
        this.fullVerticalImg = fullVerticalImg;
        this.fullHorizontalImg = fullHorizontalImg;
    }

    public Hero(Integer id, String name, String localized_name, String smallImg, String largeImg, String fullVerticalImg, String fullHorizontalImg) {
        this.id = id;
        this.name = name;
        this.localized_name = localized_name;
        this.smallImg = smallImg;
        this.largeImg = largeImg;
        this.fullVerticalImg = fullVerticalImg;
        this.fullHorizontalImg = fullHorizontalImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", localized_name='" + localized_name + '\'' +
                ", smallImg='" + smallImg + '\'' +
                ", largeImg='" + largeImg + '\'' +
                ", fullVerticalImg='" + fullVerticalImg + '\'' +
                ", fullHorizontalImg='" + fullHorizontalImg + '\'' +
                '}';
    }
}

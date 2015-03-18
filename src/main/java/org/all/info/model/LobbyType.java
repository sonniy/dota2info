package org.all.info.model;

import javax.persistence.*;

@Entity
@Table(name = "lobbyType")
public class LobbyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int id;
    private String name;

    public LobbyType() {
    }

    public LobbyType(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

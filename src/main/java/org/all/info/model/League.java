package org.all.info.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "leagues")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;

    private String tournament_url;

    @OneToMany(mappedBy = "league")
    private Set<Match> matches = new HashSet<>();

    public League() {
    }

    public League(String name, String description, String tournament_url) {
        this.name = name;
        this.description = description;
        this.tournament_url = tournament_url;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTournament_url() {
        return tournament_url;
    }

    public void setTournament_url(String tournament_url) {
        this.tournament_url = tournament_url;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }
}

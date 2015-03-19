package org.all.info.model;

import javax.persistence.*;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long match_id;
    private String season;
    private Boolean radiant_win;
    private Long duration;
    private Long start_time;
    private Integer tower_status_radiant;
    private Integer tower_status_dire;
    private Integer barracks_status_radiant;
    private Integer barracks_status_dire;
    private Integer cluster;
    private Integer first_blood_time;
    private Integer human_players;
    private Integer positive_votes;
    private Integer negative_votes;
    private Integer picks_bans;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lobby_type")
    private LobbyType lobbyType;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_mode")
    private  GameMode gameMode;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "leagueid")
    private League league;

    public Match() {
    }

    public Match(String season, Boolean radiant_win, Long duration, Long start_time,
                 Integer tower_status_radiant, Integer tower_status_dire, Integer barracks_status_radiant,
                 Integer barracks_status_dire, Integer cluster, Integer first_blood_time, Integer human_players,
                 Integer positive_votes, Integer negative_votes, Integer picks_bans, LobbyType lobbyType,
                 GameMode gameMode, League league) {
        this.season = season;
        this.radiant_win = radiant_win;
        this.duration = duration;
        this.start_time = start_time;
        this.tower_status_radiant = tower_status_radiant;
        this.tower_status_dire = tower_status_dire;
        this.barracks_status_radiant = barracks_status_radiant;
        this.barracks_status_dire = barracks_status_dire;
        this.cluster = cluster;
        this.first_blood_time = first_blood_time;
        this.human_players = human_players;
        this.positive_votes = positive_votes;
        this.negative_votes = negative_votes;
        this.picks_bans = picks_bans;
        this.lobbyType = lobbyType;
        this.gameMode = gameMode;
        this.league = league;
    }

    public Long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Boolean getRadiant_win() {
        return radiant_win;
    }

    public void setRadiant_win(Boolean radiant_win) {
        this.radiant_win = radiant_win;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Integer getTower_status_radiant() {
        return tower_status_radiant;
    }

    public void setTower_status_radiant(Integer tower_status_radiant) {
        this.tower_status_radiant = tower_status_radiant;
    }

    public Integer getTower_status_dire() {
        return tower_status_dire;
    }

    public void setTower_status_dire(Integer tower_status_dire) {
        this.tower_status_dire = tower_status_dire;
    }

    public Integer getBarracks_status_radiant() {
        return barracks_status_radiant;
    }

    public void setBarracks_status_radiant(Integer barracks_status_radiant) {
        this.barracks_status_radiant = barracks_status_radiant;
    }

    public Integer getBarracks_status_dire() {
        return barracks_status_dire;
    }

    public void setBarracks_status_dire(Integer barracks_status_dire) {
        this.barracks_status_dire = barracks_status_dire;
    }

    public Integer getCluster() {
        return cluster;
    }

    public void setCluster(Integer cluster) {
        this.cluster = cluster;
    }

    public Integer getFirst_blood_time() {
        return first_blood_time;
    }

    public void setFirst_blood_time(Integer first_blood_time) {
        this.first_blood_time = first_blood_time;
    }

    public Integer getHuman_players() {
        return human_players;
    }

    public void setHuman_players(Integer human_players) {
        this.human_players = human_players;
    }

    public Integer getPositive_votes() {
        return positive_votes;
    }

    public void setPositive_votes(Integer positive_votes) {
        this.positive_votes = positive_votes;
    }

    public Integer getNegative_votes() {
        return negative_votes;
    }

    public void setNegative_votes(Integer negative_votes) {
        this.negative_votes = negative_votes;
    }

    public Integer getPicks_bans() {
        return picks_bans;
    }

    public void setPicks_bans(Integer picks_bans) {
        this.picks_bans = picks_bans;
    }

    public LobbyType getLobbyType() {
        return lobbyType;
    }

    public void setLobbyType(LobbyType lobbyType) {
        this.lobbyType = lobbyType;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}

package org.all.info.model;

import javax.persistence.*;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long match_id;
    private Long match_seq_num;
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
    private Integer id_lobby_type;
    private Integer game_mode;
    private Integer leagueid;

    public Match() {
    }

    public Match(Long match_seq_num, String season, Boolean radiant_win, Long duration, Long start_time,
                 Integer tower_status_radiant, Integer tower_status_dire, Integer barracks_status_radiant,
                 Integer barracks_status_dire, Integer cluster, Integer first_blood_time, Integer human_players,
                 Integer positive_votes, Integer negative_votes, Integer picks_bans, Integer id_lobby_type, Integer game_mode,
                 Integer leagueid) {
        this.match_seq_num = match_seq_num;
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
        this.id_lobby_type = id_lobby_type;
        this.game_mode = game_mode;
        this.leagueid = leagueid;
    }

    public Long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public Long getMatch_seq_num() {
        return match_seq_num;
    }

    public void setMatch_seq_num(Long match_seq_num) {
        this.match_seq_num = match_seq_num;
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

    public Integer getId_lobby_type() {
        return id_lobby_type;
    }

    public void setId_lobby_type(Integer id_lobby_type) {
        this.id_lobby_type = id_lobby_type;
    }

    public Integer getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(Integer game_mode) {
        this.game_mode = game_mode;
    }

    public Integer getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(Integer leagueid) {
        this.leagueid = leagueid;
    }
}
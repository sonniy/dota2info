package org.all.info.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matchID")
public class MatchID {
    @Id
    @Column(name = "match_seq_num")
    private Long match_seq_num;
    @Column(name = "match_id")
    private Long match_id;
    @Column(name = "isParsed")
    private Boolean isParsed;

    public MatchID() {
    }

    public MatchID(Long match_seq_num, Long match_id, Boolean isParsed) {
        this.match_seq_num = match_seq_num;
        this.match_id = match_id;
        this.isParsed = isParsed;
    }

    public Long getMatch_seq_num() {
        return match_seq_num;
    }

    public void setMatch_seq_num(Long match_seq_num) {
        this.match_seq_num = match_seq_num;
    }

    public Long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public Boolean getIsParsed() {
        return isParsed;
    }

    public void setIsParsed(Boolean isParsed) {
        this.isParsed = isParsed;
    }
}

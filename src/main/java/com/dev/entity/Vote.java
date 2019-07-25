package com.dev.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "voter")
    private TopCollegue votant;

    @Enumerated(EnumType.STRING)
    private IndicatifVote vote;

    @ManyToOne
    @JoinColumn(name = "elected")
    private TopCollegue elu;

    public Vote() {
    }

    public Vote(IndicatifVote vote, TopCollegue elu) {
        this.vote = vote;
        this.elu = elu;
    }

    public TopCollegue getVotant() {
        return votant;
    }

    public void setVotant(TopCollegue votant) {
        this.votant = votant;
    }

    public IndicatifVote getVote() {
        return vote;
    }

    public void setVote(IndicatifVote vote) {
        this.vote = vote;
    }

    public TopCollegue getElu() {
        return elu;
    }

    public void setElu(TopCollegue elu) {
        this.elu = elu;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vote [votant=");
        builder.append(votant);
        builder.append(", vote=");
        builder.append(vote);
        builder.append(", elu=");
        builder.append(elu);
        builder.append("]");
        return builder.toString();
    }

}

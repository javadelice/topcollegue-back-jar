package com.dev.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "matricule_topCollegue")
    private TopCollegue votant;
    private IndicatifVote vote;
    private TopCollegue elu;

    public Vote() {
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

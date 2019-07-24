package com.dev.entity;

public class Vote {

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

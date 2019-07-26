package com.dev.entity;

public class VoteFront {
    
    private String elu;
    private String typeVote;
    private String votant;
    
    public VoteFront() {
        
    }

    public VoteFront(String matriculeVotant, String typeVote, String matriculeElu) {
        super();
        this.votant = matriculeVotant;
        this.typeVote = typeVote;
        this.elu = matriculeElu;
    }

    public String getElu() {
        return elu;
    }

    public void setElu(String elu) {
        this.elu = elu;
    }

    public String getTypeVote() {
        return typeVote;
    }

    public void setTypeVote(String typeVote) {
        this.typeVote = typeVote;
    }

    public String getVotant() {
        return votant;
    }

    public void setVotant(String votant) {
        this.votant = votant;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("VoteFront [elu=");
        builder.append(elu);
        builder.append(", typeVote=");
        builder.append(typeVote);
        builder.append(", votant=");
        builder.append(votant);
        builder.append("]");
        return builder.toString();
    }

    

}

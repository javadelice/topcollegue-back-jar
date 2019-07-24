package com.dev.entity;

import java.util.Set;

public class TopCollegue {

    private String matricule;
    private String nom;
    private String prenoms;
    private String photoUrl;
    private Set<Vote> listeVotes;

    public TopCollegue() {
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Set<Vote> getListeVotes() {
        return listeVotes;
    }

    public void setListeVotes(Set<Vote> listeVotes) {
        this.listeVotes = listeVotes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TopCollegue [matricule=");
        builder.append(matricule);
        builder.append(", nom=");
        builder.append(nom);
        builder.append(", prenoms=");
        builder.append(prenoms);
        builder.append(", photoUrl=");
        builder.append(photoUrl);
        builder.append(", listeVotes=");
        builder.append(listeVotes);
        builder.append("]");
        return builder.toString();
    }

}
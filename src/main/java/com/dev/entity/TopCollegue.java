package com.dev.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "topCollegue")
public class TopCollegue {

    @Id
    private String matricule;
    private String nomUtilisateur;
    private String motDePasse;
    private String nom;
    private String prenoms;
    private String photoUrl;
    @Transient
    private List<String> roles = new ArrayList<>();
    @OneToMany(mappedBy = "votant")
    private Set<Vote> listeVotes;

    public TopCollegue() {
        this.listeVotes = new HashSet<>();
    }

    public TopCollegue(String matricule, String nomUtilisateur, String motDePasse, String nom, String prenoms, String photoUrl, List<String> roles) {
        this();
        this.matricule = matricule;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenoms = prenoms;
        this.photoUrl = photoUrl;
        this.roles = roles;
    }

    public String getMatricule() {
        return matricule;
    }

    public void addVote(Vote vote) {
        this.listeVotes.add(vote);
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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
        builder.append(", nomUtilisateur=");
        builder.append(nomUtilisateur);
        builder.append(", motDePasse=");
        builder.append(motDePasse);
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

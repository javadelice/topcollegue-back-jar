package com.dev.dto;

public class EluScoreDTO {

    private String matricule;
    private String nom;
    private String prenom;
    private String photo;
    private Integer score;

    public EluScoreDTO(String matricule, String nom, String prenom, String photo, Integer score) {
        super();
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.score = score;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public EluScoreDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matricule == null) ? 0 : matricule.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EluScoreDTO other = (EluScoreDTO) obj;
        if (matricule == null) {
            if (other.matricule != null)
                return false;
        } else if (!matricule.equals(other.matricule))
            return false;
        return true;
    }

}

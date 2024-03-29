package com.dev.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.TopCollegue;

@Repository
public interface TopCollegueRepository extends JpaRepository<TopCollegue, String> {

    Optional<TopCollegue> findByNomUtilisateur(String nomUtilisateur);
    TopCollegue findByMatricule(String matricule);

}

package com.dev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entity.TopCollegue;
import com.dev.persistence.TopCollegueRepository;

@Service
public class TopCollegueService {

    @Autowired
    TopCollegueRepository topCollegueRepository;

    public TopCollegueService() {
    }

    public TopCollegue creerTopCollegue(TopCollegue topCollegue) {
        return this.topCollegueRepository.save(topCollegue);
    }

    public Optional<TopCollegue> findByNomUtilisateur(String nomUtilisateur) {
        return this.topCollegueRepository.findByNomUtilisateur(nomUtilisateur);
    }

}

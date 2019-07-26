package com.dev.controller;

import java.util.List;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.IndicatifVote;
import com.dev.entity.TopCollegue;
import com.dev.entity.Vote;
import com.dev.entity.VoteFront;
import com.dev.persistence.TopCollegueRepository;
import com.dev.persistence.VoteRepository;
import com.dev.util.*;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping(path = "/topcollegues")
public class TopCollegueController {

    @Autowired
    private TopCollegueRepository topCollegueRepository;

    @Autowired
    private VoteRepository voteRepository;

    @PostConstruct
    public void init() {
        System.out.println(topCollegueRepository);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/me")
    public Optional<TopCollegue> getMe() {
        return topCollegueRepository.findByNomUtilisateur(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/collegues")
    public List<TopCollegue> getCollegues() {
        return topCollegueRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/votes")
    public List<Vote> getVotes() {
        return voteRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/vote")
    public void postVote(@RequestBody VoteFront vote) { // renvoi le nombre de tentatives restantes
        // List<Vote> elusListe = voteRepository.findByVotant(vote.getVotant());
        /*
         * if(elusListe.isEmpty()) {
         * 
         * // Vérification du vote // réalisés avec les annotations manyToOne
         * voteRepository.save(vote); //return Constantes.NOMBRE_MAXIMUM_VOTE-1; return
         * 1; }else { /* }else if (elusListe.size() < Constantes.NOMBRE_MAXIMUM_VOTE) {
         * 
         * voteRepository.save(vote); }return
         * elusListe.size()-Constantes.NOMBRE_MAXIMUM_VOTE--;
         */
        // voteRepository.save(vote);} */

//        TopCollegue votantParam = topCollegueRepository.findByMatricule(vote.getMatriculeVotant());
//        TopCollegue eluParam = topCollegueRepository.findByMatricule(vote.getMatriculeElu());
        Vote leVote = new Vote();

        if ("POSITIF".equals(vote.getTypeVote())) {
            leVote = new Vote(topCollegueRepository.findByMatricule(vote.getVotant()), IndicatifVote.POSITIF, topCollegueRepository.findByMatricule(vote.getElu()));
        } else {
            leVote = new Vote(topCollegueRepository.findByMatricule(vote.getVotant()), IndicatifVote.NEGATIF, topCollegueRepository.findByMatricule(vote.getElu()));
             }
        voteRepository.saveAndFlush(leVote);

    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/vote")
    public Vote patchVote(Vote vote) {
       // List<Vote> elusListe = voteRepository.findByVotant(topCollegueRepository.findByMatricule());
        if (true) {
            voteRepository.save(vote);
        } else {
        }

        return null;
    }

}

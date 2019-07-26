package com.dev.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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


import com.dev.dto.EluScoreDTO;
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
        return topCollegueRepository
                .findByNomUtilisateur(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/score")
    public List<EluScoreDTO> getScore() {

        List<EluScoreDTO> resultats = new ArrayList<>();

        List<Vote> listeVotes = voteRepository.findAll();

        for (Vote vote : listeVotes) {

            EluScoreDTO eluScore = new EluScoreDTO(vote.getElu().getMatricule(), vote.getElu().getNom(),
                    vote.getElu().getPrenoms(), vote.getElu().getPhotoUrl(), 0);

            int indexElu = resultats.indexOf(eluScore);

            if (indexElu >= 0) {
                eluScore = resultats.get(indexElu);
            } else {
                resultats.add(eluScore);
            }

            Integer ancienScore = eluScore.getScore();
            Integer nouveauScore = 0;

            // ajout du score
            if (vote.getVote().equals(IndicatifVote.POSITIF)) {
                nouveauScore = ancienScore + 100;
            } else {
                nouveauScore = ancienScore - 100;
            }

            eluScore.setScore(nouveauScore);

        }

        Collections.sort(resultats, Comparator.comparing(EluScoreDTO::getScore).reversed());

        List<EluScoreDTO> resultatsFiltre = new ArrayList<>();

        for (EluScoreDTO eluScoreDTO : resultats) {

            if (resultatsFiltre.size() < 3) {
                resultatsFiltre.add(eluScoreDTO);
            }

        }

        return resultatsFiltre;

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

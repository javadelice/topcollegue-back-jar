package com.dev.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.EluScoreDTO;
import com.dev.entity.IndicatifVote;
import com.dev.entity.TopCollegue;
import com.dev.entity.Vote;
import com.dev.persistence.TopCollegueRepository;
import com.dev.persistence.VoteRepository;

@RestController
@RequestMapping(path = "/topcollegues")
public class TopCollegueController {

    @Autowired
    private TopCollegueRepository topCollegueRepository;

    @Autowired
    private VoteRepository voteRepository;

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

}

package com.dev.service;

import java.util.Arrays;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entity.IndicatifVote;
import com.dev.entity.TopCollegue;
import com.dev.entity.Vote;
import com.dev.persistence.TopCollegueRepository;
import com.dev.persistence.VoteRepository;

@Service
public class TopCollegueService {

    @Autowired
    private TopCollegueRepository topCollegueRepository;

    @Autowired
    private VoteRepository voteRepository;

    public TopCollegueService() {
    }

    @PostConstruct
    public void init() {
        TopCollegue tp1 = new TopCollegue("132", "dj", "dj", "DUPONT", "Jean", "https://randomuser.me/api/portraits/men/41.jpg",
                Arrays.asList("ROLE_ADMIN", "ROLE_USER"));

        TopCollegue tp2 = new TopCollegue("123", "rj", "rj", "RAY", "John", "https://randomuser.me/api/portraits/men/42.jpg",
                Arrays.asList("ROLE_ADMIN", "ROLE_USER"));

        TopCollegue tp3 = new TopCollegue("111", "sj", "sj", "SMITH", "Jack", "https://randomuser.me/api/portraits/men/43.jpg",
                Arrays.asList("ROLE_USER"));

        TopCollegue tp4 = new TopCollegue("222", "mm", "mm", "MARTIN", "Martin", "https://randomuser.me/api/portraits/men/44.jpg",
                Arrays.asList("ROLE_USER"));

        TopCollegue tp5 = new TopCollegue("128", "dc", "dc", "DUPRE", "Céline", "https://randomuser.me/api/portraits/women/45.jpg",
                Arrays.asList("ROLE_USER"));

        TopCollegue tp6 = new TopCollegue("218", "dj2", "dj2", "DUVAL", "Juliette", "https://randomuser.me/api/portraits/women/46.jpg",
                Arrays.asList("ROLE_USER"));

        TopCollegue tp7 = new TopCollegue("112", "dm", "dm", "DESPRES", "Megane", "https://randomuser.me/api/portraits/women/47.jpg",
                Arrays.asList("ROLE_USER"));

        TopCollegue tp8 = new TopCollegue("107", "dm2", "dm2", "DULAC", "Maïté", "https://randomuser.me/api/portraits/women/48.jpg",
                Arrays.asList("ROLE_USER"));

        Vote v1 = new Vote(tp1, IndicatifVote.POSITIF, tp2);
        Vote v2 = new Vote(tp1, IndicatifVote.NEGATIF, tp3);
        Vote v3 = new Vote(tp1, IndicatifVote.POSITIF, tp4);
        Vote v4 = new Vote(tp2, IndicatifVote.NEGATIF, tp1);
        Vote v5 = new Vote(tp2, IndicatifVote.NEGATIF, tp3);

        topCollegueRepository.save(tp1);
        topCollegueRepository.save(tp2);
        topCollegueRepository.save(tp3);
        topCollegueRepository.save(tp4);
        topCollegueRepository.save(tp5);
        topCollegueRepository.save(tp6);
        topCollegueRepository.save(tp7);
        topCollegueRepository.save(tp8);

        voteRepository.save(v1);
        voteRepository.save(v2);
        voteRepository.save(v3);
        voteRepository.save(v4);
        voteRepository.save(v5);

    }

    public TopCollegue creerTopCollegue(TopCollegue topCollegue) {
        return this.topCollegueRepository.save(topCollegue);
    }

    public Optional<TopCollegue> findByNomUtilisateur(String nomUtilisateur) {
        return this.topCollegueRepository.findByNomUtilisateur(nomUtilisateur);
    }

}

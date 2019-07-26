package com.dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.TopCollegue;
import com.dev.entity.Vote;
import com.dev.persistence.TopCollegueRepository;
import com.dev.persistence.VoteRepository;
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping(path = "/topcollegues")
public class TopCollegueController {

    @Autowired
    private TopCollegueRepository topCollegueRepository;
    
    @Autowired
    private VoteRepository voteRepository;

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
    
    

}

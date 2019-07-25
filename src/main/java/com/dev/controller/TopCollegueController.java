package com.dev.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.TopCollegue;
import com.dev.persistence.TopCollegueRepository;

@RestController
@RequestMapping(path = "/topcollegues")
public class TopCollegueController {

    @Autowired
    private TopCollegueRepository topCollegueRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/me")
    public Optional<TopCollegue> getMe() {
        return topCollegueRepository.findByNomUtilisateur(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}

package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dev.persistence.TopCollegueRepository;
import com.dev.persistence.VoteRepository;

@Component
public class FindByVotantDemo {

    @Autowired
    private static VoteRepository voteRepository;
    
    @Autowired
    private static TopCollegueRepository topCollegueRepository;
    
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        
//        test = voteRepository.findByVotant(voteRepository.findById(123))
//        System.out.println(topCollegueRepository.findByMatricule("123").);
        System.out.println(topCollegueRepository);
    }
    
}

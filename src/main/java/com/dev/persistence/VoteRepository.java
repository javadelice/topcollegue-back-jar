package com.dev.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.TopCollegue;
import com.dev.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    
   List<Vote> findByElu(TopCollegue elu);
   List<Vote> findByVotant(TopCollegue votant);
}

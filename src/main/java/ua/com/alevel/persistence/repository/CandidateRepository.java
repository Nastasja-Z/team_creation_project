package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Candidate;

@Repository
public interface CandidateRepository extends BaseRepository<Candidate>{

    Candidate findByNameOfCandidate(String nameOfCandidate);
}

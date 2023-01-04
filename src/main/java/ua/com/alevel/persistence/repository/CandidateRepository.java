package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Candidate;

import java.util.List;

@Repository
public interface CandidateRepository extends BaseRepository<Candidate>{

    Candidate findByNameOfCandidate(String nameOfCandidate);

    List<Candidate> findByProjects_Id(Long id);
}

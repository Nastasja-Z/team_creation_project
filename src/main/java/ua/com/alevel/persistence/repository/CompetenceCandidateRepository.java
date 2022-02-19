package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.Candidate;
import ua.com.alevel.persistence.entity.Competence;
import ua.com.alevel.persistence.entity.references.CompetenceCandidate;

@Repository
public interface CompetenceCandidateRepository extends BaseRepository<CompetenceCandidate> {

    CompetenceCandidate findByCandidateAndCompetence(Candidate candidate, Competence competence);

    @Modifying
    @Transactional
    @Query("delete from CompetenceCandidate c where c.candidate.id = :id")
    void deleteByCandidate(@Param("id") Long id);
}

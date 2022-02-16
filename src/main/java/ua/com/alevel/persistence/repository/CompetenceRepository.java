package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Competence;

import java.util.Collection;

@Repository
public interface CompetenceRepository extends BaseRepository<Competence> {

    //may not work
    @Query("FROM Competence c WHERE c.candidate.candidateId = :candidateId ORDER BY c.competenceName")
    Collection<Competence> findAllByCandidateId(@Param("candidateId") Long candidateId);
}

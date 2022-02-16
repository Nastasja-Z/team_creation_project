package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Competence;

import java.util.Collection;

@Repository
public interface CompetenceRepository extends BaseRepository<Competence> {

    //may not work
    /*
    * @Query("select t from Test t join User u where u.username = :username")
List<Test> findAllByUsername(@Param("username")String username);
    * */
    //@Query("FROM Competence c WHERE c.candidate.candidateId = :candidateId ORDER BY c.competenceName")
    @Query("select competence from Competence competence join Candidate candidate where candidate.id = :id")
    Collection<Competence> findAllByCandidateId(@Param("id") Long id);
}

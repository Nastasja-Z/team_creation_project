package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.Project;

@Repository
public interface ProjectRepository extends BaseRepository<Project> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Project p set p.willingness = true where p.id = :projectId")
    void setOfReadyToCreateTheTeam(@Param("projectId") Long projectId);
}

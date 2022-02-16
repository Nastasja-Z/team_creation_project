package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Indicator;
import ua.com.alevel.persistence.entity.Project;

import java.util.Collection;

@Repository
public interface ProjectRepository extends BaseRepository<Project> {

    @Query("FROM Indicator p WHERE p.project.projectId = :projectId ORDER BY p.nameOfProject")
    Collection<Indicator> findAllByCandidateId(@Param("projectId") Long projectId);
}

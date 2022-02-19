package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Indicator;
import ua.com.alevel.persistence.entity.Project;

import java.util.Collection;

@Repository
public interface IndicatorRepository extends BaseRepository<Indicator> {

    Collection<Indicator> findByProjects_Project(Project project);
    /*@Query("FROM Indicator p WHERE p.project.projectId = :projectId ORDER BY p.nameOfProject")
    Collection<Indicator> findAllByProjectId(@Param("id") Long projectId);*/
}

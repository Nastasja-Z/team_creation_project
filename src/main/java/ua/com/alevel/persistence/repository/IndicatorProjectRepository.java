package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.Indicator;
import ua.com.alevel.persistence.entity.references.IndicatorProject;
import ua.com.alevel.persistence.entity.Project;

@Repository
public interface IndicatorProjectRepository extends BaseRepository<IndicatorProject> {

    //Collection<IndicatorProject> findAllByProject(Project project);
    IndicatorProject findByProjectAndIndicator(Project project, Indicator indicator);

    @Modifying
    @Transactional
    @Query("delete from IndicatorProject i where i.project.id = :id")
    void deleteByProject(@Param("id") Long id);
}

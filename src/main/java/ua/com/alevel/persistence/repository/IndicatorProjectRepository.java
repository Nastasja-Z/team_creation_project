package ua.com.alevel.persistence.repository;

import ua.com.alevel.persistence.entity.Indicator;
import ua.com.alevel.persistence.entity.IndicatorProject;
import ua.com.alevel.persistence.entity.Project;

import java.util.Collection;

public interface IndicatorProjectRepository extends BaseRepository<IndicatorProject> {

    Collection<IndicatorProject> findAllByProject(Project project);
    IndicatorProject findByProjectAndIndicator(Project project, Indicator indicator);
}

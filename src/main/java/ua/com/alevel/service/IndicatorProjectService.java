package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.references.IndicatorProject;

public interface IndicatorProjectService extends BaseService<IndicatorProject> {

    IndicatorProject findByProjectAndIndicator(Long projectId, Long indicatorId);

    void deleteAllByProject(Long id);
}

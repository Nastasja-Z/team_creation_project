package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.IndicatorProject;

import java.util.List;

public interface IndicatorProjectService extends BaseService<IndicatorProject> {

    List<IndicatorProject> findAllByProject(Long id);
    IndicatorProject findByProjectAndIndicator(Long projectId, Long indicatorId);
}

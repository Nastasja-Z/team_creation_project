package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Indicator;

import java.util.Collection;

public interface IndicatorService extends BaseService<Indicator> {

    Collection<Indicator> findAllByProjectId(Long projectId);
}

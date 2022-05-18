package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Project;

public interface ProjectService extends BaseService<Project> {

    void setOfTheWillingnessToCreation(Long projectId);
}

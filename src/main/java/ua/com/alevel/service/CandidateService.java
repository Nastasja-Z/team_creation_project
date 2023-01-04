package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Candidate;
import ua.com.alevel.persistence.entity.Project;

import java.util.List;

public interface CandidateService extends BaseService<Candidate> {
    List<Candidate> findAllByProject(Long projectId);
}

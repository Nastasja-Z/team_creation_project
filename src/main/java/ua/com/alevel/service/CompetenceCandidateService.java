package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Candidate;
import ua.com.alevel.persistence.entity.references.CompetenceCandidate;

public interface CompetenceCandidateService extends BaseService<CompetenceCandidate>{

    CompetenceCandidate findByCandidateAndCompetence(Long candidateId, Long competenceId);

    void deleteAllByCandidate(Long id);
}

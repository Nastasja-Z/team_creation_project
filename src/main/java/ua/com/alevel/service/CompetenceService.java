package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Competence;

import java.util.Collection;

public interface CompetenceService extends BaseService<Competence> {

    Collection<Competence> findAllByCandidateId(Long candidateId);

    //void deleteFromReference(Long id);

}

package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNonExistsException;
import ua.com.alevel.persistence.entity.Competence;
import ua.com.alevel.persistence.repository.CandidateRepository;
import ua.com.alevel.persistence.repository.CompetenceRepository;
import ua.com.alevel.service.CompetenceService;

import java.util.Collection;

@Service
public class CompetenceServiceImpl implements CompetenceService {

    private final CompetenceRepository competenceRepository;
    private final CandidateRepository candidateRepository;

    public CompetenceServiceImpl(
            CompetenceRepository competenceRepository,
            CandidateRepository candidateRepository
    ) {
        this.competenceRepository = competenceRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void create(Competence entity) {
        competenceRepository.save(entity);
    }

    @Override
    public void update(Competence entity) {
        checkByExist(entity.getId());
        competenceRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        checkByExist(id);
        competenceRepository.deleteById(id);
    }

    @Override
    public Competence findById(Long id) {
        checkByExist(id);
        return competenceRepository.findById(id).get();
    }

    @Override
    public Collection<Competence> findAll() {
        return competenceRepository.findAll();
    }

    @Override
    public Collection<Competence> findAllByCandidateId(Long candidateId) {
        return competenceRepository.findByCandidates_Id(candidateId);
    }

    private boolean checkByExist(Long id) {
        if (!competenceRepository.existsById(id)) {
            try {
                throw new EntityNonExistsException("no competence such this one");
            } catch (EntityNonExistsException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}

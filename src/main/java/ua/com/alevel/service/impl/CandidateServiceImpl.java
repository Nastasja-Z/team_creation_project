package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNonExistsException;
import ua.com.alevel.persistence.entity.Candidate;
import ua.com.alevel.persistence.repository.CandidateRepository;
import ua.com.alevel.service.CandidateService;

import java.util.Collection;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void create(Candidate entity) {
        candidateRepository.save(entity);
    }

    @Override
    public void update(Candidate entity) {
        checkByExist(entity.getId());
        candidateRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        checkByExist(id);
        candidateRepository.deleteById(id);
    }

    @Override
    public Candidate findById(Long id) {
        checkByExist(id);
        return candidateRepository.findById(id).get();
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    private boolean checkByExist(Long id) {
        if (!candidateRepository.existsById(id)) {
            try {
                throw new EntityNonExistsException("no candidate such this one");
            } catch (EntityNonExistsException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}

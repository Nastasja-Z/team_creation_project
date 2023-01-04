package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.persistence.entity.Candidate;
import ua.com.alevel.persistence.entity.references.CompetenceCandidate;
import ua.com.alevel.persistence.repository.CandidateRepository;
import ua.com.alevel.persistence.repository.CompetenceCandidateRepository;
import ua.com.alevel.persistence.repository.CompetenceRepository;
import ua.com.alevel.service.CompetenceCandidateService;

import java.util.Collection;

@Service
public class CompetenceCandidateServiceImpl implements CompetenceCandidateService {

    private final CompetenceRepository competenceRepository;
    private final CandidateRepository candidateRepository;
    private final CompetenceCandidateRepository competenceCandidateRepository;

    public CompetenceCandidateServiceImpl(
            CompetenceRepository competenceRepository,
            CandidateRepository candidateRepository,
            CompetenceCandidateRepository competenceCandidateRepository
    ) {
        this.competenceRepository = competenceRepository;
        this.candidateRepository = candidateRepository;
        this.competenceCandidateRepository = competenceCandidateRepository;
    }

    @Override
    public void create(CompetenceCandidate entity) {
        competenceCandidateRepository.save(entity);
    }

    @Override
    public void update(CompetenceCandidate entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CompetenceCandidate findById(Long id) {
        return null;
    }

    @Override
    public Collection<CompetenceCandidate> findAll() {
        return competenceCandidateRepository.findAll();
    }

    @Override
    public CompetenceCandidate findByCandidateAndCompetence(Long candidateId, Long competenceId) {
        return competenceCandidateRepository.findByCandidateAndCompetence(
                candidateRepository.findById(candidateId).get(),
                competenceRepository.findById(competenceId).get()
        );
    }

    @Override
    public void deleteAllByCandidate(Long candidateId) {
        competenceCandidateRepository.deleteByCandidate(candidateId);
    }
}

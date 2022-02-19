package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.CompetenceCandidateFacade;
import ua.com.alevel.persistence.entity.references.CompetenceCandidate;
import ua.com.alevel.service.CandidateService;
import ua.com.alevel.service.CompetenceCandidateService;
import ua.com.alevel.service.CompetenceService;
import ua.com.alevel.web.dto.response.CompetenceCandidateResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetenceCandidateFacadeImpl implements CompetenceCandidateFacade {

    private final CompetenceService competenceService;
    private final CandidateService candidateService;
    private final CompetenceCandidateService competenceCandidateService;

    public CompetenceCandidateFacadeImpl(
            CompetenceService competenceService,
            CandidateService candidateService,
            CompetenceCandidateService competenceCandidateService
    ) {
        this.competenceService = competenceService;
        this.candidateService = candidateService;
        this.competenceCandidateService = competenceCandidateService;
    }

    @Override
    public void create(CompetenceCandidate competenceCandidate) {

    }

    @Override
    public void update(CompetenceCandidate competenceCandidate, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CompetenceCandidateResponseDto findById(Long id) {
        return null;
    }

    @Override
    public List<CompetenceCandidateResponseDto> findAllByCandidateId(Long id) {
        List<CompetenceCandidateResponseDto> dtos = new ArrayList<>();
        competenceService.findAllByCandidateId(id).forEach(x -> {
            dtos.add(new CompetenceCandidateResponseDto(x,
                    competenceCandidateService
                            .findByCandidateAndCompetence(candidateService
                                    .findById(id)
                                    .getId(), x.getId())));
        });
        return dtos;
    }

    @Override
    public void deleteByCandidateId(Long id) {
        competenceCandidateService.deleteAllByCandidate(id);
    }
}

package ua.com.alevel.algorithm;

import org.paukov.combinatorics3.Generator;
import org.springframework.stereotype.Service;

import ua.com.alevel.facade.CompetenceCandidateFacade;
import ua.com.alevel.facade.IndicatorProjectFacade;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Candidate;
import ua.com.alevel.service.CandidateService;
import ua.com.alevel.service.ProjectService;
import ua.com.alevel.web.dto.response.CompetenceCandidateResponseDto;
import ua.com.alevel.web.dto.response.IndicatorProjectResponseDto;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AlgorithmService extends BaseEntity {

    private final IndicatorProjectFacade indicatorProjectFacade;
    private final CompetenceCandidateFacade competenceCandidateFacade;
    private final ProjectService projectService;
    private final CandidateService candidateService;

    private Map<Long, Map<String, Integer>> forCandidates;
    private Map<String, Integer> ipMap;
    private Map<String, Integer> ccMap;
    private List<Map<String, Integer>> mapList;
    private List<List<Map<String, Integer>>> result;
    private int temp = 0, currentFixedPosition = 0;
    private List<List<Map<String, Integer>>> resultedSource = new ArrayList<>();
    private List<Map<String, Integer>> sumHolder;
    private List<Candidate> resCandidates;

    public AlgorithmService(IndicatorProjectFacade indicatorProjectFacade, CompetenceCandidateFacade competenceCandidateFacade, ProjectService projectService, CandidateService candidateService) {
        this.indicatorProjectFacade = indicatorProjectFacade;
        this.competenceCandidateFacade = competenceCandidateFacade;
        this.projectService = projectService;
        this.candidateService = candidateService;
    }

    public Map<String, Integer> setIndProjMap(Long projectId) {
        ipMap = new TreeMap<>();
        List<IndicatorProjectResponseDto> dtos = indicatorProjectFacade.findAllByProjectId(projectId);
        for (IndicatorProjectResponseDto dto : dtos) {
            ipMap.put(dto.getIndicatorName(), dto.getLevel());
        }
        return ipMap;
    }

    public Map<String, Integer> setCanComMap(Long candidateId) {
        ccMap = new TreeMap<>();
        List<CompetenceCandidateResponseDto> dtos = competenceCandidateFacade.findAllByCandidateId(candidateId);
        for (CompetenceCandidateResponseDto dto : dtos) {
            ccMap.put(dto.getCompetenceName(), dto.getLevel());
        }
        ccMap.entrySet().removeIf(entry -> !ipMap.containsKey(entry.getKey()));
        extraMap(candidateId);
        return ccMap;
    }

    private void extraMap(Long candidateId) {
        ipMap.forEach((k, v) -> {
            if (!ccMap.containsKey(k)) {
                ccMap.put(k, 0);
            }
        });
    }

    public List<Map<String, Integer>> fullListOfCompetencesByCandidates(Long projectId) {
        mapList = new ArrayList<>();
        forCandidates = new HashMap<>();
        for (Candidate candidate : candidateService.findAllByProject(projectId)) {
            mapList.add(setCanComMap(candidate.getId()));
            forCandidates.put(candidate.getId(), setCanComMap(candidate.getId()));
        }
        return mapList;
    }

    private void extraTwo() {
        if (currentFixedPosition != temp) {
            for (int i = currentFixedPosition + 1; i <= temp; i++) {
                System.out.println(mapList.get(i));
            }
        }
        System.out.println();
        temp++;
    }

    public void creationOfMatrixNetwork(Long projectId) {
        int max = 0;
        result = new ArrayList<>();
        for (int i = 1; i < mapList.size(); i++) {
            Generator.combination(mapList)
                    .simple(i)
                    .stream()
                    .forEach(x -> result.add(x));
        }
        result.add(mapList);

        List<Integer> ip = ipMap.values().stream().toList();
        System.out.println(ip);
        System.out.println();

        int sourceSize = result.size();
        int current = 0;
        while (sourceSize != 0) {
            List<Map<String, Integer>> ccc = result.get(current);
            current++;
            int[] array = new int[ip.size()];
            for (int i : array) {
                i = 0;
            }
            for (Map<String, Integer> map : ccc) {
                List<Integer> c = map.values().stream().toList();
                for (int i = 0; i < ip.size(); i++) {
                    if (ip.get(i) > c.get(i)) {
                        array[i] += 0;
                    } else {
                        array[i] += 1;
                    }
                }
            }
            boolean check = false;
            for (int j : array) {
                if (j == 0) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("RESULT " + current + " " + false);
            } else {
                resultedSource.add(ccc);
                List<Candidate> candidates = new ArrayList<>();
                ccc.forEach(x -> {
                    candidates.add(getCandidates(x));
                });
                int laboriousness = 0, cost = 0;
                int labOfProject = projectService.findById(projectId).getLengthInWeeks();
                for (Candidate candidate : candidates) {
                    laboriousness += candidate.getEmployment() * labOfProject;
                    cost += candidate.getEmployment() * labOfProject * candidate.getSalaryProHour().intValue();
                    System.out.println(candidate.getNameOfCandidate());
                }
                if (laboriousness >= projectService.findById(projectId).getLaboriousness()
                        && cost <= projectService.findById(projectId).getBudget().intValue()) {
                    System.out.println();
                    System.out.println(laboriousness);
                    System.out.println(cost);
                    int sum = 0;
                    for (Map<String, Integer> map : ccc) {
                        List<Integer> c = map.values().stream().toList();
                        for (int i = 0; i < ip.size(); i++) {
                            if (ip.get(i) <= c.get(i)) {
                                sum += c.get(i);
                            }
                        }
                        System.out.println("sum = " + sum);
                    }
                    if (sum > max) {
                        max = sum;
                        sumHolder = ccc;
                        resCandidates = candidates;
                    }
                }
                System.out.println();
            }
            sourceSize--;
            check = false;
        }
        System.out.println("max = " + max);
        for (Candidate resCandidate : resCandidates) {
            System.out.println(resCandidate.getNameOfCandidate());
        }
        System.out.println();
        for (Map<String, Integer> map : sumHolder) {
            System.out.println(map);
        }
    }

    public List<Candidate> getResCandidates(){
        return resCandidates;
    }

    public List<Map<String, Integer>> getSumHolder(){
        return sumHolder;
    }

    private List<Candidate> extraThree(List<Map<String, Integer>> ccc) {
        List<Candidate> candidates = new ArrayList<>();
        ccc.forEach(x -> {
            candidates.add(getCandidates(x));
        });
        return candidates;
    }


    private Candidate getCandidates(Map<String, Integer> temp) {
        AtomicReference<Candidate> candidate = new AtomicReference<>(new Candidate());
        forCandidates.forEach((key, value) -> {
            if (value.equals(temp)) {
                candidate.set(candidateService.findById(key));
            }
        });
        return candidate.get();
    }

}


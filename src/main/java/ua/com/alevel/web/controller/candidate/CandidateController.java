package ua.com.alevel.web.controller.candidate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.algorithm.AlgorithmService;
import ua.com.alevel.facade.CompetenceCandidateFacade;
import ua.com.alevel.facade.IndicatorProjectFacade;
import ua.com.alevel.persistence.entity.Candidate;
import ua.com.alevel.persistence.entity.Competence;
import ua.com.alevel.persistence.entity.references.CompetenceCandidate;
import ua.com.alevel.service.CandidateService;
import ua.com.alevel.service.CompetenceService;
import ua.com.alevel.service.IndicatorService;
import ua.com.alevel.service.ProjectService;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    //CONVERT TO FACADES
    private final CandidateService candidateService;
    private final CompetenceService competenceService;
    private final ProjectService projectService;
    private final IndicatorProjectFacade indicatorProjectFacade;
    private final AlgorithmService algorithmService;
    private final CompetenceCandidateFacade competenceCandidateFacade;
    private final IndicatorService indicatorService ;

    public CandidateController(CandidateService candidateService, CompetenceService competenceService, ProjectService projectService, IndicatorProjectFacade indicatorProjectFacade, AlgorithmService algorithmService, CompetenceCandidateFacade competenceCandidateFacade, IndicatorService indicatorService) {
        this.candidateService = candidateService;
        this.competenceService = competenceService;
        this.projectService = projectService;
        this.indicatorProjectFacade = indicatorProjectFacade;
        this.algorithmService = algorithmService;
        this.competenceCandidateFacade = competenceCandidateFacade;
        this.indicatorService = indicatorService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("candidates", candidateService.findAll());
        return "pages/candidates/candidates_all";
    }


    @GetMapping("/{projectId}")
    public String findAllWithPreId(Model model, @PathVariable("projectId") Long projectId) {
        model.addAttribute("candidates", candidateService.findAll());
        model.addAttribute("project", projectService.findById(projectId));
        return "pages/candidates/candidates_all";
    }

    @GetMapping("/chosen/{projectId}")
    public String findAllByProject(Model model, @PathVariable("projectId") Long projectId) {

        model.addAttribute("project", projectService.findById(projectId));
        model.addAttribute("filteredCandidates", candidateService.findAllByProject(projectId));

        model.addAttribute("algorithm", algorithmService.setIndProjMap(projectId));
        model.addAttribute("algorithmed", algorithmService.fullListOfCompetencesByCandidates(projectId));
        algorithmService.creationOfMatrixNetwork(projectId);
        return "pages/candidates/candidates_by_project";
    }


    @GetMapping("/result/{projectId}")
    public String resultOfAlgorithm(Model model, @PathVariable Long projectId){
        algorithmService.creationOfMatrixNetwork(projectId);
        model.addAttribute("project", projectService.findById(projectId));
        model.addAttribute("candidates", algorithmService.getResCandidates());
        model.addAttribute("competences", algorithmService.getSumHolder());
        return "pages/result";
    }

    @GetMapping("/getCandidateDetails/{candidateId}")
    public String getCandidateDetails(Model model, @PathVariable("candidateId") Long candidateId) {
        model.addAttribute("candidate", candidateService.findById(candidateId));
        model.addAttribute("competences", competenceCandidateFacade.findAllByCandidateId(candidateId));
        model.addAttribute("competence", new Competence());
        model.addAttribute("indicators", indicatorService.findAll());
        return "pages/candidates/candidate_details";
    }

    @GetMapping("/new/{projectId}")
    public String redirectToNewCandidate(Model model, @PathVariable("projectId") Long projectId) {
        model.addAttribute("candidate", new Candidate());
        model.addAttribute("project", projectService.findById(projectId));
        return "pages/candidates/candidate_new";
    }

    @PostMapping("/new/{projectId}")
    public String createCandidate(@ModelAttribute("candidate") Candidate candidate, @PathVariable("projectId") Long projectId) {
        candidateService.create(candidate);
        //in the future to details to add new competence
        return "redirect:/candidates/{projectId}";
    }

    @PostMapping("/update")
    public String updateCandidate(@ModelAttribute("candidate") Candidate candidate) {
        candidateService.update(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        competenceCandidateFacade.deleteByCandidateId(id);
        candidateService.delete(id);
        return "redirect:/candidates";
    }

    // COMPETENCES

    @PostMapping("/{candidateId}/newCompetence")
    public String createCompetence(
            @ModelAttribute("competence") Competence competence,
            @ModelAttribute("level") Integer level,
            @PathVariable Long candidateId
    ) {
        competenceService.create(competence);
        CompetenceCandidate competenceCandidate = new CompetenceCandidate();
        competenceCandidate.setCompetence(competence);
        competenceCandidate.setCandidate(candidateService.findById(candidateId));
        competenceCandidate.setLevel(level);
        competenceCandidateFacade.create(competenceCandidate);
        return "redirect:/candidates/getCandidateDetails/{candidateId}";
    }

    @GetMapping("/deleteCompetence/{id}")
    public String deleteCompetence(@PathVariable Long id) {
        //TODO: delete from references table, NOT from origin
        //it is false, remake below variant to right
        competenceService.delete(id);
        return "redirect:/candidates";  //redirect to candidates/candidate_details ???
    }

    //HOW INSERT INTO MANY TO MANY

   /* @PostMapping("/newCompetence")
    public String createCompetence(@ModelAttribute("competence") Competence competence, @ModelAttribute("candidate") Candidate candidate) {
        competence.setCandidate(candidate);
        competenceService.create(competence);
        return "redirect:/candidates";
    }

    @RequestMapping(value = "/newCompetence", method = RequestMethod.GET)
    public String displayCompetence(Model model) {
        model.addAttribute("competence", new Competence());
        Collection<Candidate> candidates = candidateService.findAll();
        model.addAttribute("candidates", candidates);
        return "pages/candidates/competence_new";
    }*/

    @PostMapping("/updateCompetence")
    public String updateCompetence(@ModelAttribute("competence") Competence competence) {
        competenceService.update(competence);
        return "redirect:/candidates";
    }

}
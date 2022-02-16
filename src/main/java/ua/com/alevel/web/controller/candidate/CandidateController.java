package ua.com.alevel.web.controller.candidate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.persistence.entity.Candidate;
import ua.com.alevel.persistence.entity.Competence;
import ua.com.alevel.service.CandidateService;
import ua.com.alevel.service.CompetenceService;

import java.util.Collection;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    private final CompetenceService competenceService;

    public CandidateController(CandidateService candidateService, CompetenceService competenceService) {
        this.candidateService = candidateService;
        this.competenceService = competenceService;
    }

    @GetMapping
    public String findAll(Model model) {
        Collection<Candidate> candidates = candidateService.findAll();
        model.addAttribute("candidates", candidates);
        return "pages/candidates/candidates_all";
    }

    /*@GetMapping("/allCompetences")
    public String getFullInfoAboutCandidate(Model model) {
        Collection<Candidate> candidates=candidateService.findAll();
        Collection<Competence> competences=competenceService.findAll();
        model.addAttribute("competences", competences);
        model.addAttribute("candidates", candidates);
        return "all_competences";
    }*/

    @GetMapping("/getCandidateDetails/{candidateId}")
    public String getCandidateDetails(Model model, @PathVariable("candidateId") Long candidateId) {
        model.addAttribute("candidate", candidateService.findById(candidateId));
        model.addAttribute("competences", competenceService.findAllByCandidateId(candidateId));
        return "pages/candidates/candidate_details";
    }

    @GetMapping("/new")
    public String redirectToNewCandidate(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "pages/candidates/candidate_new";
    }

    @PostMapping("/new")
    public String createCandidate(@ModelAttribute("candidate") Candidate candidate) {
        candidateService.create(candidate);
        return "redirect:/candidates";
    }

    @PostMapping("/update")
    public String updateCandidate(@ModelAttribute("candidate") Candidate candidate) {
        candidateService.update(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        candidateService.delete(id);
        return "redirect:/candidates";
    }

    // COMPETENCES

    @GetMapping("/deleteCompetence/{id}")
    public String deleteCompetence(@PathVariable Long id) {
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
package ua.com.alevel.web.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.facade.IndicatorProjectFacade;
import ua.com.alevel.persistence.entity.Indicator;
import ua.com.alevel.persistence.entity.Project;
import ua.com.alevel.service.IndicatorService;
import ua.com.alevel.service.ProjectService;

import java.util.Date;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    //ADD FACADES !!!!!!!!!!!
    private final ProjectService projectService;
    private final IndicatorService indicatorService;

    private final IndicatorProjectFacade indicatorProjectFacade ;

    public ProjectController(ProjectService projectService, IndicatorService indicatorService, IndicatorProjectFacade indicatorProjectFacade) {
        this.projectService = projectService;
        this.indicatorService = indicatorService;
        this.indicatorProjectFacade = indicatorProjectFacade;
    }


    //do i need it??????? maybe for main page to introduce some of our closed projects
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "pages/projects/projects_all";
    }

    @GetMapping("/getProjectDetails/{projectId}")
    public String getProjectDetails(Model model, @PathVariable("projectId") Long projectId) {
        model.addAttribute("project", projectService.findById(projectId));
        model.addAttribute("indicators", indicatorProjectFacade.findAllByProjectId(projectId));
        return "pages/projects/project_details";
    }

    @GetMapping("/new")
    public String redirectToNewProject(Model model) {
        model.addAttribute("project", new Project());
        return "pages/projects/project_new";
    }

  /*  @GetMapping("/all_indicators")
    public String getFullInfoAboutProject(Model model) {
        model.addAttribute("indicators", indicatorService.findAll());
        return "all_indicators";
    }*/

    @PostMapping("/new")
    public String createProject(@ModelAttribute("project") Project project) {
        projectService.create(project);
        return "redirect:/projects";
    }

    @GetMapping("/update/{id}")
    public String updateProject(@PathVariable("projectId") Long projectId, Model model){
        model.addAttribute("project", projectService.findById(projectId));
        return "pages/projects/project_details";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project") Project project) {
        projectService.update(project);
        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        indicatorProjectFacade.deleteByProjectId(id);
        projectService.delete(id);
        return "redirect:/projects";
    }

    // INDICATORS

    @GetMapping("/deleteIndicator/{id}")
    public String deleteIndicator(@PathVariable Long id) {
        indicatorService.delete(id);
        return "redirect:/projects";
    }

    /*@PostMapping("/newIndicator")
    public String createIndicator(@ModelAttribute("indicator") Indicator indicator, @ModelAttribute("project") Project project) {
        indicator.setProject(project);
        indicatorService.create(indicator);
        return "redirect:/projects";
    }

    @RequestMapping(value = "/newIndicator", method = RequestMethod.GET)
    public String displayIndicator(Model model) {
        model.addAttribute("project", new Project());
        Collection<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "pages/projects/projects_new";
    }*/

    @PostMapping("/updateIndicator")
    public String updateIndicator(@ModelAttribute("indicator") Indicator indicator) {
        indicatorService.update(indicator);
        return "redirect:/projects";
    }
}

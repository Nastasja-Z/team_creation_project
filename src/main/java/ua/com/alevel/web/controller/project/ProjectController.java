package ua.com.alevel.web.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.facade.IndicatorProjectFacade;
import ua.com.alevel.persistence.entity.Indicator;
import ua.com.alevel.persistence.entity.Project;
import ua.com.alevel.persistence.entity.references.IndicatorProject;
import ua.com.alevel.service.IndicatorService;
import ua.com.alevel.service.ProjectService;
import ua.com.alevel.service.UserService;


@Controller
@RequestMapping("/projects")
public class ProjectController {

    //TODO: REMAKE WITH FACADES !!!!!!!!!!!
    private final ProjectService projectService;
    private final IndicatorService indicatorService;
    private final UserService userService;

    private final IndicatorProjectFacade indicatorProjectFacade;

    public ProjectController(ProjectService projectService, IndicatorService indicatorService, UserService userService, IndicatorProjectFacade indicatorProjectFacade) {
        this.projectService = projectService;
        this.indicatorService = indicatorService;
        this.userService = userService;
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
        model.addAttribute("indicator", new Indicator());
        // TODO: ADD PM & HR, die darauf verantwortlich sind
        model.addAttribute("users", userService.findByProjectId(projectId));
        return "pages/projects/project_details";
    }

    @GetMapping("/new")
    public String redirectToNewProject(Model model) {
        model.addAttribute("project", new Project());
        return "pages/projects/project_new";
    }

    @PostMapping("/new")
    public String createProject(@ModelAttribute("project") Project project) {
        projectService.create(project);
        return "redirect:/projects";
    }

    @GetMapping("/update/{id}")
    public String updateProject(@PathVariable("projectId") Long projectId, Model model) {
        model.addAttribute("project", projectService.findById(projectId));
        return "pages/projects/project_details";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project") Project project) {
        projectService.update(project);
        return "redirect:/projects";
    }

    /*
    //SAMPLE
       @PostMapping("/update/{id}")
    public String redirectToUpdatedProduct(@ModelAttribute("product") ProductRequestDto productRequestDto, @PathVariable Integer id) {
        productFacade.update(productRequestDto, id);
        return "redirect:/products/details/{id}";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(Model model, @PathVariable Integer id) {
        model.addAttribute("product", productFacade.findById(id));
        model.addAttribute("categories", Category.values());
        return "pages/products/product_update";
    }
    * */

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        indicatorProjectFacade.deleteByProjectId(id);
        projectService.delete(id);
        return "redirect:/projects";
    }

    @RequestMapping(value = "/ready/{projectId}", method = RequestMethod.GET)
    public String setOfTheWillingness(@PathVariable Long projectId) {
        //TODO: add proj to user
        //TODO:somewhere check if user is HR

        //TODO: check it after the print of the list of the right users for the projects

       projectService.setOfTheWillingnessToCreation(projectService.findById(projectId).getId());

        //TODO: send a msg to the PM that HR has received a query to create the team (maybe with alert)
        return "redirect:/projects/getProjectDetails/{projectId}";
    }

    // INDICATORS

    //@GetMapping("/{projectId}/deleteIndicator/{indicatorId}")
    @RequestMapping(value = "/{projectId}/deleteIndicator/{indicatorId}", method = RequestMethod.GET)
    public String deleteIndicator(@PathVariable("projectId") Long projectId,
                                  @PathVariable("indicatorId") Long indicatorId
    ) {
        indicatorProjectFacade.deleteByIndicatorAndProjectId(projectService
                .findById(projectId)
                .getId(), indicatorId);
        return "redirect:/projects/getProjectDetails/{projectId}";
    }

    @PostMapping("/{projectId}/newIndicator") //add to project by id
    public String createIndicator(
            @ModelAttribute("indicator") Indicator indicator,
            @ModelAttribute("level") Integer level,
            @PathVariable Long projectId
    ) {
        //вынести в request dto. OR NOT
        indicatorService.create(indicator);
        IndicatorProject indicatorProject = new IndicatorProject();
        indicatorProject.setIndicator(indicator);
        indicatorProject.setProject(projectService.findById(projectId)); //project null
        indicatorProject.setLevel(level);
        indicatorProjectFacade.create(indicatorProject);
        return "redirect:/projects/getProjectDetails/{projectId}";
    }

    @PostMapping("/updateIndicator")
    public String updateIndicator(@ModelAttribute("indicator") Indicator indicator) {
        indicatorService.update(indicator);
        return "redirect:/projects";
    }
}

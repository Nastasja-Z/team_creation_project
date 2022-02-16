package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity{

    @Column(name = "project_name")
    private String nameOfProject;

    @OneToMany(mappedBy = "project")
    private Set<Indicator> indicators;

    @Column(nullable = false)
    private BigDecimal budget;

    @Column(name = "start_project")
    private Date startOfProject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "end_project") // nullable??
    private Date endOfProject;    // validate!!!

    public String getNameOfProject() {
        return nameOfProject;
    }

    public void setNameOfProject(String nameOfProject) {
        this.nameOfProject = nameOfProject;
    }

    public Set<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<Indicator> indicators) {
        this.indicators = indicators;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Date getStartOfProject() {
        return startOfProject;
    }

    public void setStartOfProject(Date startOfProject) {
        this.startOfProject = startOfProject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getEndOfProject() {
        return endOfProject;
    }

    public void setEndOfProject(Date endOfProject) {
        this.endOfProject = endOfProject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Project project = (Project) o;
        return Objects.equals(nameOfProject, project.nameOfProject) && Objects.equals(indicators, project.indicators) && Objects.equals(budget, project.budget) && Objects.equals(startOfProject, project.startOfProject) && Objects.equals(user, project.user) && Objects.equals(endOfProject, project.endOfProject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameOfProject, indicators, budget, startOfProject, user, endOfProject);
    }
}

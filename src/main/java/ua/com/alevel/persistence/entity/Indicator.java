package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "indicators")
public class Indicator extends  BaseEntity{

    @Column(name = "indicator_name")
    private String indicatorName;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    private Integer level;

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Indicator indicator = (Indicator) o;
        return Objects.equals(indicatorName, indicator.indicatorName) && Objects.equals(project, indicator.project) && Objects.equals(level, indicator.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), indicatorName, project, level);
    }
}

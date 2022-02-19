package ua.com.alevel.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "indicators_projects")
public class IndicatorProject extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "indicator_id")
    Indicator indicator;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

    int level;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IndicatorProject that = (IndicatorProject) o;
        return level == that.level && Objects.equals(indicator, that.indicator) && Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), indicator, project, level);
    }
}

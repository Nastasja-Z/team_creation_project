package ua.com.alevel.web.dto.response;

import ua.com.alevel.persistence.entity.Indicator;
import ua.com.alevel.persistence.entity.references.IndicatorProject;

public class IndicatorProjectResponseDto extends ResponseDto {

    private String indicatorName;
    private int level;

    public IndicatorProjectResponseDto(
            Indicator indicator,
            IndicatorProject indicatorProject) {
        super(indicatorProject.getProject().getId());
        this.indicatorName = indicator.getIndicatorName();
        this.level = indicatorProject.getLevel();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }
}

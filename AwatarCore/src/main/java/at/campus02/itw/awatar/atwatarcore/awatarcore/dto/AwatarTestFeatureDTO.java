package at.campus02.itw.awatar.atwatarcore.awatarcore.dto;

import java.util.List;

public class AwatarTestFeatureDTO {

    private String id;
    private String featureName;
    private List<AwatarTestScenarioDTO> testcases;

    public AwatarTestFeatureDTO(String id, String featureName, List<AwatarTestScenarioDTO> testcases) {
        this.id = id;
        this.featureName = featureName;
        this.testcases = testcases;
    }

    public AwatarTestFeatureDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public List<AwatarTestScenarioDTO> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<AwatarTestScenarioDTO> testcases) {
        this.testcases = testcases;
    }
}

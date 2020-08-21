package at.campus02.itw.awatar.atwatarcore.awatarcore.dto;

import java.util.List;

public class AwatarTestScenarioDTO {

    private String id;
    private String scenario;  //Testcase name
    private String additionalDescription;

    private List<AwatarTestStepDTO> given;
    private List<AwatarTestStepDTO> when;
    private List<AwatarTestStepDTO> then;

    public AwatarTestScenarioDTO(String id, String scenario, String additionalDescription, List<AwatarTestStepDTO> given, List<AwatarTestStepDTO> when, List<AwatarTestStepDTO> then) {
        this.id = id;
        this.scenario = scenario;
        this.additionalDescription = additionalDescription;
        this.given = given;
        this.when = when;
        this.then = then;
    }

    public AwatarTestScenarioDTO() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }

    public List<AwatarTestStepDTO> getGiven() {
        return given;
    }

    public void setGiven(List<AwatarTestStepDTO> given) {
        this.given = given;
    }

    public List<AwatarTestStepDTO> getWhen() {
        return when;
    }

    public void setWhen(List<AwatarTestStepDTO> when) {
        this.when = when;
    }

    public List<AwatarTestStepDTO> getThen() {
        return then;
    }

    public void setThen(List<AwatarTestStepDTO> then) {
        this.then = then;
    }
}

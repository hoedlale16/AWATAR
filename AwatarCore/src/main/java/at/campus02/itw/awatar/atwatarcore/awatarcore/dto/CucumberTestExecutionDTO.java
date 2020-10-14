package at.campus02.itw.awatar.atwatarcore.awatarcore.dto;

import java.util.List;

public class CucumberTestExecutionDTO {

    private String feature;
    private String scenario;
    private List<String> given;
    private List<String> when;
    private List<String> then;

    private int exitState;
    private String result;

    public String getFeature() { return feature; }
    public void setFeature(String feature) { this.feature = feature; }

    public String getScenario() { return scenario; }
    public void setScenario(String scenario) { this.scenario = scenario; }

    public List<String> getGiven() { return given; }
    public void setGiven(List<String> given) { this.given = given; }

    public List<String> getWhen() { return when; }
    public void setWhen(List<String> when) { this.when = when; }

    public List<String> getThen() { return then; }
    public void setThen(List<String> then) { this.then = then; }

    public int getExitState() {
        return exitState;
    }
    public void setExitState(int exitState) {
        this.exitState = exitState;
    }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result;}
}

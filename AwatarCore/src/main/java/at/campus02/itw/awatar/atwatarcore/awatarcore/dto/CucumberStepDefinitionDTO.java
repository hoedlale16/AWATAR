package at.campus02.itw.awatar.atwatarcore.awatarcore.dto;

import java.util.List;

public class CucumberStepDefinitionDTO {

    private String methodName;
    private String stepDefinition;

    private List<String> parameters;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStepDefinition() {
        return stepDefinition;
    }

    public void setStepDefinition(String stepDefinition) {
        this.stepDefinition = stepDefinition;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}



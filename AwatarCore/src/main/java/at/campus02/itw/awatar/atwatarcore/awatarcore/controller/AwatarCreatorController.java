package at.campus02.itw.awatar.atwatarcore.awatarcore.controller;

import at.campus02.itw.awatar.atwatarcore.awatarcore.dto.CucumberStepDefinitionDTO;
import at.campus02.itw.awatar.atwatarcore.awatarcore.facade.AwatarCreatorFacade;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController()
@RequestMapping(value = "/awatar/creator")
public class AwatarCreatorController {


    @Autowired
    private AwatarCreatorFacade awatarCreatorFacade;


    @RequestMapping(value = "/stepDefinitions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getApplicationData() {

        Map<String, Object> applicationData = new HashMap<>();

        //Add available executable step definitions
        Map<String, List<CucumberStepDefinitionDTO>>  stepDefinitions = new HashMap<>();
        stepDefinitions.put("GIVEN", collectStepDefintion(Given.class.getName()));
        stepDefinitions.put("WHEN", collectStepDefintion(When.class.getName()));
        stepDefinitions.put("THEN", collectStepDefintion(Then.class.getName()));
        applicationData.put("stepDefinition", stepDefinitions);


        //TODO: add further avialable information (e.g. stored test cased and test scenarios)
        //applicationData.put("testScenarios", awatarCreatorFacade.getAllTestScenarios());

        return applicationData;
    }

    private List<CucumberStepDefinitionDTO> collectStepDefintion(final String className) {

        final List<CucumberStepDefinitionDTO> stepDefinitions = new ArrayList<>();

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(CucumberOptions.class));
        for (BeanDefinition beanDefinition : scanner.findCandidateComponents("at.campus02.itw.awatar.atwatarcore.awatarcore.cucumber")){

            final AnnotationMetadata controllerAnnotationMetaData = (((ScannedGenericBeanDefinition) beanDefinition).getMetadata());


            //Show available Methods
            final Set<MethodMetadata> methodMetadataSet = controllerAnnotationMetaData.getAnnotatedMethods(className);
            if(methodMetadataSet != null && ! methodMetadataSet.isEmpty()) {
                for(MethodMetadata m : methodMetadataSet) {
                    Map<String, Object> methodAttributes = m.getAnnotationAttributes( className);
                    if(methodAttributes != null && ! methodAttributes.isEmpty()) {
                        CucumberStepDefinitionDTO stepDefinition = new CucumberStepDefinitionDTO();
                        stepDefinition.setMethodName(m.getMethodName());

                        final String description = (String)methodAttributes.get("value");
                        stepDefinition.setStepDefinition(description);

                        List<String> parameters = new ArrayList<>();
                        Matcher matcher = Pattern.compile("\\{([^}]+)\\}").matcher(description);
                        while(matcher.find()) {
                            parameters.add(matcher.group(1));
                        }
                        stepDefinition.setParameters(parameters);


                        stepDefinitions.add(stepDefinition);
                    }
                }
            }

        }

        return stepDefinitions;
    }

}

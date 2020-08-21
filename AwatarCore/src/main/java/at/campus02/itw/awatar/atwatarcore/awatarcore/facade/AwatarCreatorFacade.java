package at.campus02.itw.awatar.atwatarcore.awatarcore.facade;

import at.campus02.itw.awatar.atwatarcore.awatarcore.dto.AwatarTestFeatureDTO;
import at.campus02.itw.awatar.atwatarcore.awatarcore.dto.AwatarTestScenarioDTO;
import at.campus02.itw.awatar.atwatarcore.awatarcore.dto.AwatarTestStepDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service()
@Transactional
public class AwatarCreatorFacade {

    public List<AwatarTestFeatureDTO> getAllTestScenarios() {

        final ArrayList<AwatarTestFeatureDTO> testScenarios = new ArrayList<>();

        //TODO: load them from a DB or something...
        final AwatarTestFeatureDTO feature = new AwatarTestFeatureDTO();
        feature.setId("feature_1");
        feature.setFeatureName("Test-Feature");


        final AwatarTestScenarioDTO scenarioDTO = new AwatarTestScenarioDTO();
        scenarioDTO.setId("test_1");
        scenarioDTO.setScenario("Testcase Excample");
        scenarioDTO.setAdditionalDescription("This is an Testcase Example");
        scenarioDTO.setGiven(Collections.singletonList(new AwatarTestStepDTO(1, "User opens login page.", "user_open_page(http://www.mypage.at)")));
        scenarioDTO.setWhen(Collections.singletonList(new AwatarTestStepDTO(1, "User enters credentions.", "login_to_page(user,password)")));
        scenarioDTO.setThen(Collections.singletonList(new AwatarTestStepDTO(1, "Dhasboard Page is shown", "check_dashboard_page()")));


        return testScenarios;
    }
}

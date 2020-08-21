package at.campus02.itw.awatar.atwatarcore.awatarcore.controller;

import at.campus02.itw.awatar.atwatarcore.awatarcore.dto.AwatarTestScenarioDTO;
import at.campus02.itw.awatar.atwatarcore.awatarcore.facade.AwatarCreatorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AwatarCreatorController {

    @Autowired
    private AwatarCreatorFacade awatarCreatorFacade;

    @RequestMapping(value = "/applicationData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getApplicationData() throws Exception {

        Map<String, Object> applicationData = new HashMap<>();

        //Add global application data
        //applicationData.put("availableTestMethods", Arrays.asList(QuotationState.values()));
        applicationData.put("testScenarios", awatarCreatorFacade.getAllTestScenarios());

        return applicationData;
    }

    /*@PostMapping("/projects")
    ResponseEntity<AwatarTestScenarioDTO> create(@RequestBody @Valid AwatarTestScenarioDTO dto) {
        return new ResponseEntity(awatarCreatorFacade.create(dto), HttpStatus.CREATED);
    }*/
}

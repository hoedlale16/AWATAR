package at.campus02.itw.awatar.atwatarcore.awatarcore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping(value = "/awatar/runner")
public class AwatarRunnerController {


    @RequestMapping(value = "execute", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity recalulatePriceOfPositions(@RequestBody String feature) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Feature: Is it Friday yet?").append("\n")
          .append(   "Everybody wants to know when it's Friday").append("\n")
          .append("Scenario: Sunday isn't Friday").append("\n")
          .append("Given today is Sunday").append("\n")
          .append("When I ask whether it's Friday yet").append("\n")
        .append("Then I should be told Nope");

        final String featureText = sb.toString();

        return new ResponseEntity(featureText, HttpStatus.OK);
    }
}

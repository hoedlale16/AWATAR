package at.campus02.itw.awatar.atwatarcore.awatarcore.controller;

import at.campus02.itw.awatar.atwatarcore.awatarcore.dto.CucumberTestExecutionDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping(value = "/awatar/runner")
public class AwatarRunnerController {


    @RequestMapping(value = "execute", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity executeTestFeature(@RequestBody CucumberTestExecutionDTO executionDTO) {
        try {
            //Create temporary feature file with given content to execute cucumber runtime...
            final String featureText = parseFeatureContent(executionDTO);
            final File featureFile = File.createTempFile("cucumber-", "-execution.feature");
            FileUtils.writeStringToFile(featureFile, featureText, Charset.defaultCharset(), false);
            featureFile.deleteOnExit();

            //Execute cucumber with given file...
            executeCucumber(executionDTO, featureFile);
            return (executionDTO.getExitState() != 0) ? new ResponseEntity(executionDTO, HttpStatus.OK) : new ResponseEntity(executionDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private String parseFeatureContent(final CucumberTestExecutionDTO executionDTO) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Feature: ").append(executionDTO.getFeature()).append("\n")
          .append("Scenario: ").append(executionDTO.getScenario()).append("\n");

        buildStepDefinitions(sb, executionDTO.getGiven(), "Given");
        buildStepDefinitions(sb, executionDTO.getWhen(), "When");
        buildStepDefinitions(sb, executionDTO.getThen(), "Then");

         return sb.toString();
    }

    private void buildStepDefinitions(final StringBuilder stringBuilder, List<String> stepDefinitions, final String identifier ) {
        //Create a local copy...
        final List<String> sdList = new ArrayList<>(stepDefinitions);

        if (CollectionUtils.isNotEmpty(sdList)) {
            final String firstStepDefinition= sdList.get(0);
            stringBuilder.append(identifier).append(" ").append(firstStepDefinition).append("\n");
            sdList.remove(0);

            //If further elements available append with 'And'
            if(! sdList.isEmpty()) {
                sdList.forEach(sd -> stringBuilder.append("And ").append(sd).append("\n") );
            }
        }

    }

    private void executeCucumber(final CucumberTestExecutionDTO executionDTO, final File featureFile) {
        Runnable task = () -> {
            // Redirect System.OUT of Cucumber Execution to response
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(buffer));

            //Execute Cucumber runtime
            String[] args = { featureFile.getAbsolutePath(), "--glue", "at.campus02.itw.awatar.atwatarcore.awatarcore.cucumber" };
            final int exitState = io.cucumber.core.cli.Main.run(args, Thread.currentThread().getContextClassLoader());
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

            // Use captured content for Response
            final String executionResult = buffer.toString();
            final String executionResultNoColor = executionResult.replaceAll("\u001B\\[[;\\d]*m", "");

            executionDTO.setResult(executionResultNoColor);
            executionDTO.setExitState(exitState);
            buffer.reset();
        };

        //Execute Cucumber Runtime in seperate thread...
        task.run();
        Thread thread = new Thread(task);
        thread.start();
    }
}

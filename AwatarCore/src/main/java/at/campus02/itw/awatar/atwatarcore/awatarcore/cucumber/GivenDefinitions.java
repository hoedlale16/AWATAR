package at.campus02.itw.awatar.atwatarcore.awatarcore.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
//import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions
public class GivenDefinitions {


  @Given("Login page with url {string} is shown")
  public void login_page_is_shown(String url) {
    //final WebDriver driver = new ChromeDriver();

    System.setProperty("webdriver.chrome.driver","lib/chromedriver");

    //driver.get(url);
  }
}

package at.campus02.itw.awatar.atwatarcore.awatarcore.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(publish = false)
public class GivenDefinitions {

  @Given("Login page with url {string} is shown")
  public void login_page_with_url_is_shown(String url) {

      WebDriver driver = new ChromeDriver();
      driver.get(url);
  }

    @Given("Webserver is available")
    public void webserver_is_available() {
    }


}

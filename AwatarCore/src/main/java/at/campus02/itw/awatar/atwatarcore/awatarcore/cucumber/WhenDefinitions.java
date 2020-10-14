package at.campus02.itw.awatar.atwatarcore.awatarcore.cucumber;

import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@RunWith(Cucumber.class)
@CucumberOptions(publish = false)
public class WhenDefinitions {

    //private final WebDriver driver = new ChromeDriver();

    @When("User with username {string} and password {string} log into")
    public void user_with_username_and_password_log_into(String username, String password) {

    }

    @When("Open website with URL {string} ")
    public void open_website_with_url(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
    }
}

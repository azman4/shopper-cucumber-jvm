package weatherShopper.StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import weatherShopper.CommonLibrary.WebHelper;
import weatherShopper.Pages.HomePage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class HomePageSteps extends RunnerSteps {

    WebDriver driver = getDriver();
    HomePage homePage;
    WebHelper helper = new WebHelper(driver);


    @After
    public void end(Scenario scenario) throws IllegalMonitorStateException
    {
        if(scenario.isFailed())
        {
            Allure.addAttachment("Failed Screen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        driver.close();
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User launches the \"([^\"]*)\" site$")
    public void launch_App(String URL) {
        helper.visitWebApp(URL);
    }

    @When("User selects a product type based on the current weather")
    public void select_ProductType() throws IOException {
        homePage = new HomePage(driver);
        homePage.select_ProductType();
    }
}
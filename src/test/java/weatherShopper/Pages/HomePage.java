package weatherShopper.Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import weatherShopper.DataConfig.configInit;

import java.io.IOException;

public class HomePage {

    WebDriver driver;

    @FindBy(id = "temperature")
    WebElement currentWeatherText;
    @FindBy(xpath = "//button[text()='Buy moisturizers']")
    WebElement buyMoisturisersBtn;
    @FindBy(xpath = "//button[text()='Buy sunscreens']")
    WebElement buySunscreensBtn;

    static public String productType;
    configInit config = new configInit();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*
     * Function to select a product type based on the current weather on the screen
    */
    public void select_ProductType() throws IOException {
        String currentWeather = currentWeatherText.getText();
        String[] currentWeatherValue = currentWeather.split(" ");
        if (Integer.parseInt(currentWeatherValue[0]) <= 19) {
            Assert.assertTrue("Temperature is less than 19", true);
            buyMoisturisersBtn.click();
            productType = config.readConfigData("moisturiser");
        } else if (Integer.parseInt(currentWeatherValue[0]) >= 34) {
            Assert.assertTrue("Temperature is more than 34", true);
            buySunscreensBtn.click();
            productType = config.readConfigData("sunscreen");
        }
    }
}

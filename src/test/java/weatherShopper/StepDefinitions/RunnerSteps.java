package weatherShopper.StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.SessionId;
import weatherShopper.DataConfig.configInit;

import java.io.IOException;

public class RunnerSteps {

    configInit config = new configInit();
    public static WebDriver driver;

    public WebDriver getDriver() {
        try {
            String browserValue = config.readConfigData("browser");
            String headless = config.readConfigData("headless");

            switch (browserValue.toLowerCase()) {
                case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless.equalsIgnoreCase("true")) {
                    chromeOptions.addArguments("-headless", "-safe-mode");
                }
                if (driver == null) {
                    System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
                } else {
                    SessionId session = ((ChromeDriver) driver).getSessionId();
                    if (session == null) {
                        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
                        driver = new ChromeDriver(chromeOptions);
                        driver.manage().window().maximize();
                    }
                }
                return driver;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless.equalsIgnoreCase("true")) {
                        firefoxOptions.addArguments("-headless", "-safe-mode");
                    }
                    if (driver == null) {
                        System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
                        driver = new FirefoxDriver(firefoxOptions);
                        driver.manage().window().maximize();
                    } else {
                        SessionId session = ((FirefoxDriver) driver).getSessionId();
                        if (session == null) {
                            System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
                            driver = new FirefoxDriver(firefoxOptions);
                            driver.manage().window().maximize();
                            return driver;
                        }
                    }
                return driver;
                default:
                    System.out.println("Invalid browser name "+browserValue);
                    System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;
    }
}

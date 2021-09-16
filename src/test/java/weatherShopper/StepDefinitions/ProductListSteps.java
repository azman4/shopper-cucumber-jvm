package weatherShopper.StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import weatherShopper.Pages.HomePage;
import weatherShopper.Pages.ProductListPage;

import java.io.IOException;

public class ProductListSteps extends RunnerSteps {

    ProductListPage productList;
    WebDriver driver = getDriver();


    @Then("User gets all the products details")
        public void get_ProductDetails() {
        }

    @Then("User adds asked products to cart")
    public void add_ProductsToCart() throws IOException {
        String productType = HomePage.productType;
        productList = new ProductListPage(driver);
        productList.getProductDetails();
        if (productType.equalsIgnoreCase(config.readConfigData("moisturiser"))) {
            productList.add1stMoisturisersToCart();
            productList.add2ndMoisturisersToCart();
        }
        else if (productType.equalsIgnoreCase(config.readConfigData("sunscreen"))) {
            productList.add1stSunscreensToCart();
            productList.add2ndSunscreensToCart();
        }
    }

    @Then("User should see both products added to cart")
    public void verify_CartCount() throws IOException {
        productList.verifyCartCount();
    }
    @When("User clicks on cart button")
    public void goto_Cart() throws NoSuchElementException {
        productList.goToCart();
    }
}
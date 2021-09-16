package weatherShopper.StepDefinitions;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import weatherShopper.Pages.CartPage;
import java.io.IOException;

public class CartPageSteps extends RunnerSteps {

    WebDriver driver = getDriver();
    CartPage cartPage;

    @Then("User should see the added products in cart")
    public void verify_Cart() throws NoSuchElementException {
        cartPage = new CartPage(driver);
        cartPage.verify_AddedProducts();
    }

    @When("User submits card details for the correct total amount")
    public void pay_WithCard() throws IOException, InterruptedException {
        cartPage.pay_WithCard();
    }

    @Then("User should see the payment success page")
    public void verify_PaymentSuccess() throws IOException {
        cartPage.verify_PaymentSuccess();
    }

}
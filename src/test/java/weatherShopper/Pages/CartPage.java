package weatherShopper.Pages;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import weatherShopper.DataConfig.configInit;

import java.io.IOException;

public class CartPage {

    WebDriver driver;

    @FindBy(xpath = "//table[@class='table table-striped']//tr[1]//td[1]")
    WebElement product1NameText;
    @FindBy(xpath = "//table[@class='table table-striped']//tr[2]//td[1]")
    WebElement product2NameText;
    @FindBy(xpath = "//table[@class='table table-striped']//tr[1]//td[2]")
    WebElement product1PriceText;
    @FindBy(xpath = "//table[@class='table table-striped']//tr[2]//td[2]")
    WebElement product2PriceText;
    @FindBy(id = "total")
    WebElement totalAmount;
    @FindBy(xpath = "//button/span[text()='Pay with Card']")
    WebElement payButton;
    @FindBy(id = "email")
    WebElement emailTextBox;
    @FindBy(id = "card_number")
    WebElement cardNoTextBox;
    @FindBy(id = "cc-exp")
    WebElement expiryDateTextBox;
    @FindBy(id = "cc-csc")
    WebElement cvvTextBox;
    @FindBy(id = "billing-zip")
    WebElement pinCodeTextBox;
    @FindBy(id = "submitButton")
    WebElement submitButton;
    @FindBy(xpath = "//p[@class='text-justify']")
    WebElement paymentConfirmationText;

    configInit config = new configInit();
    static String actualTotalAmount;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*
     * Function to verify the products added to cart along with their price
     */
    public void verify_AddedProducts() throws NoSuchElementException {
        String expectedProduct1Name = ProductListPage.product1Name;
        String expectedProduct2Name = ProductListPage.product2Name;
        int expectedProduct1Price = ProductListPage.product1Price;
        int expectedProduct2Price = ProductListPage.product2Price;
        int expectedTotalAmount = expectedProduct1Price + expectedProduct2Price;

        String actualProduct1Name = product1NameText.getText();
        String actualProduct2Name = product2NameText.getText();
        String actualProduct1Price = product1PriceText.getText();
        String actualProduct2Price = product2PriceText.getText();
        actualTotalAmount = totalAmount.getText();
        actualTotalAmount = actualTotalAmount.replaceAll("[^0-9]", "");

        if (expectedProduct1Name.equalsIgnoreCase(actualProduct1Name) && expectedProduct2Name.equalsIgnoreCase(actualProduct2Name) && expectedProduct1Price == Integer.parseInt(actualProduct1Price) && expectedProduct2Price == Integer.parseInt((actualProduct2Price)) && expectedTotalAmount == Integer.parseInt(actualTotalAmount))
        {
            Assert.assertTrue("Added products are displayed on the cart page", true);
        }
        else {
            Assert.fail("Added products are NOT added to the cart");
        }
    }

    /*
     * Function to type one character at a time from a string
    */
    public void sendChar(WebElement element, String value)
    {
        element.clear();

        for (int i = 0; i < value.length(); i++){
            char c = value.charAt(i);
            String s = String.valueOf(c);
            element.sendKeys(s);
        }
    }

    /*
     * Function to submit card details and also verify the total amount of the order
    */
    public void pay_WithCard() throws NoSuchElementException, IOException, InterruptedException {
        payButton.click();
        Thread.sleep(3000);
        driver.switchTo().frame(0);

        emailTextBox.sendKeys(config.readConfigData("emailID"));
        cardNoTextBox.click();
        sendChar(cardNoTextBox, config.readConfigData("cardNumber"));
        sendChar(expiryDateTextBox, config.readConfigData("cardExpiryDate"));
        cvvTextBox.sendKeys(config.readConfigData("cardCVV"));
        pinCodeTextBox.sendKeys(config.readConfigData("pincode"));
        String totalToBePaid = submitButton.getText();
        totalToBePaid = totalToBePaid.replaceAll("[^.0-9]", "");
        float f=Float.parseFloat(totalToBePaid);
        int totalToBePaidValue = (int) f;

        if (totalToBePaidValue == Integer.parseInt(actualTotalAmount))
        {
            submitButton.click();
            Assert.assertTrue("Added products are displayed on the cart page", true);
        }
        else {
            Assert.fail("Added products are NOT added to the cart");
        }
        driver.switchTo().defaultContent();
    }

    /*
     * Function to verify the payment success page
    */
    public void verify_PaymentSuccess() throws NoSuchElementException, IOException {
        String actualPaymentText = paymentConfirmationText.getText();
        String expectedPaymentText = config.readConfigData("paymentText");

        if (actualPaymentText.equalsIgnoreCase(expectedPaymentText))
        {
            Assert.assertTrue("Payment was successful", true);
        }
        else {
            Assert.fail("Payment was NOT successful");
        }
    }
}

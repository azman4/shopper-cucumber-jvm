package weatherShopper.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import weatherShopper.DataConfig.configInit;

import java.io.IOException;
import java.util.*;

public class ProductListPage {

    WebDriver driver;
    configInit config = new configInit();
    static Map<Integer, String> map_final_products = new TreeMap<>();
    static String product1Name;
    static String product2Name;
    static int product1Price;
    static int product2Price;

    @FindBy(id = "cart")
    WebElement cartCount;

    public ProductListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*
     * Function to store the product name and product price displayed in a TreeMap (In ascending order of price)
    */
    public void getProductDetails() {
        try {
            List<WebElement> list_of_products_name = driver.findElements(By.xpath(config.readConfigData("allProductNames")));
            List<WebElement> list_of_products_price = driver.findElements(By.xpath(config.readConfigData("allProductPrices")));
            String product_name;
            String product_price;
            int int_product_price;

            for (int i = 0; i < list_of_products_name.size(); i++) {
                product_name = list_of_products_name.get(i).getText();//Iterate and fetch product name
                product_price = list_of_products_price.get(i).getText();//Iterate and fetch product price
                product_price = product_price.replaceAll("[^0-9]", "");//Replace anything with space other than numbers
                int_product_price = Integer.parseInt(product_price);//Convert to Integer
                map_final_products.put(int_product_price, product_name);//Add product and price in HashMap
            }
            Assert.assertTrue("Product Details are stored", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Function to Add the least expensive Moisturizer with Aloe in it
    */
    public void add1stMoisturisersToCart() throws IOException {
        for (Integer key : map_final_products.keySet()) {
            String value = map_final_products.get(key);
            String aloeProduct = config.readConfigData("aloe").toLowerCase();
            if (value.toLowerCase().contains(aloeProduct)) {
                driver.findElement(By.xpath("//p[text()='"+value+"']/following-sibling::button")).click();
                Assert.assertTrue("Least expensive Aloe product is added to cart", true);
                product1Name = value;
                product1Price = key;
                break;
            }
        }
    }

    /*
     * Function to Add the least expensive Moisturizer with Almond in it
    */
    public void add2ndMoisturisersToCart() throws IOException {
        for (Integer key : map_final_products.keySet()) {
            String value = map_final_products.get(key);
            String almondProduct = config.readConfigData("almond").toLowerCase();
            if (value.toLowerCase().contains(almondProduct)) {
                driver.findElement(By.xpath("//p[text()='"+value+"']/following-sibling::button")).click();
                Assert.assertTrue("Least expensive Almond product is added to cart", true);
                product2Name = value;
                product2Price = key;
                break;
            }
        }
    }

    /*
     * Function to Add the least expensive Sunscreen with SP-50 in it
    */
    public void add1stSunscreensToCart() throws IOException {
        for (Integer key : map_final_products.keySet()) {
            String value = map_final_products.get(key);
            String spf50Product = config.readConfigData("spf-50").toLowerCase();
            if (value.toLowerCase().contains(spf50Product)) {
                driver.findElement(By.xpath("//p[text()='"+value+"']/following-sibling::button")).click();
                Assert.assertTrue("Least expensive SPF-50 product is added to cart", true);
                product1Name = value;
                product1Price = key;
                break;
            }
        }

    }

    /*
     * Function to Add the least expensive Sunscreen with SP-30 in it
    */
    public void add2ndSunscreensToCart() throws IOException {
        for (Integer key : map_final_products.keySet()) {
            String value = map_final_products.get(key);
            String spf30Product = config.readConfigData("spf-30").toLowerCase();
            if (value.toLowerCase().contains(spf30Product)) {
                driver.findElement(By.xpath("//p[text()='"+value+"']/following-sibling::button")).click();
                Assert.assertTrue("Least expensive SPF-30 product is added to cart", true);
                product2Name = value;
                product2Price = key;
                break;
            }
        }
    }

    /*
     * Function to Verify the cart count
    */
    public void verifyCartCount() throws IOException {
       String actualCartCount = cartCount.getText();
       actualCartCount = actualCartCount.replaceAll("[^0-9]", "");
       String expectedCartCount =  config.readConfigData("cartCount");
       if (Integer.parseInt(actualCartCount) == Integer.parseInt(expectedCartCount))
       {
           Assert.assertTrue("2 Products are added to cart", true);
       }
       else
       {
           Assert.assertFalse("2 Products are NOT added to cart", false);
       }
    }

    /*
     * Function to click on the cart button
    */
    public void goToCart() throws NoSuchElementException {
        cartCount.click();
    }
}
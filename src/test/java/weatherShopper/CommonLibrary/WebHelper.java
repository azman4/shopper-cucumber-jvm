package weatherShopper.CommonLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import weatherShopper.DataConfig.configInit;
import weatherShopper.StepDefinitions.RunnerSteps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebHelper extends RunnerSteps {

	WebDriver driver;

	public WebHelper (WebDriver driver){
		this.driver = driver;
	}

	configInit config = new configInit();
	/*
	 *
	 * Visit app function
	 *
	 */
	public void visitWebApp(String URL) {
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			String baseUrl = config.readConfigData(URL);
			driver.get(baseUrl);
		} catch (Exception ex) {
			System.out.println("URL exception" + ex.getMessage());
		}
	}

	/*
	 *
	 * Wait for Element method
	 *
	 */
	public void waitForElement(By locator) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver,
					Integer.parseInt(config.readConfigData("explicitWait")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception ex) {
			System.out.println("Element %S not found after " + config.readConfigData("explicitWait") + " seconds");
		}

	}

	/*
	 *
	 * is element displayed
	 *
	 */
	public boolean isElementDisplayed(By locate) {

		try {
			waitForElement(locate);

			return driver.findElement(locate).isDisplayed();

		}

		catch (Exception ex) {

			System.out.println("Element - %s not found");
			return false;
		}
	}

	/*
	 *
	 * select dropdown value method
	 *
	 */
	public void selectDropDownValue(By locator, String value) {

		try {
			waitForElement(locator);
			Select dropDown = new Select(driver.findElement(locator));
			dropDown.selectByVisibleText(value);
		} catch (Exception ex) {

			System.out.println("Unable to select value %s from dropdown %s");
		}
	}

	/*
	 *
	 * Click Element
	 *
	 */
	public void clickElement(By locator) {
		try {
			waitForElement(locator);
			driver.findElement(locator).click();

		} catch (Exception ex) {
			System.out.println("Element %s not clickable");
		}
	}

	/*
	 *
	 * Send keys through action class
	 *
	 */
	public void actionMoveToElementForSendKeys(By locator, String key) {
		try {
			Actions builder = new Actions(driver);
			Actions actionTaken = builder.moveToElement(driver.findElement(locator)).click()
					.sendKeys(key);
			actionTaken.perform();
		} catch (Exception ex) {
			System.out.println("Error is"+ ex);
		}
	}

	/*
	 *
	 * send keys
	 *
	 */
	public void sendKeys(By locator, String key) {
		try {
			driver.findElement(locator).click();

			driver.findElement(locator).sendKeys(key);
		} catch (Exception ex) {
			System.out.println("Unable to send %s to %s element");
		}
	}

	/*
	 *
	 * get Attribute
	 *
	 */
	public String getAttribute(By locator, String attribute) {
		try {
			return driver.findElement(locator).getAttribute(attribute);
		} catch (Exception ex) {
			System.out.println(String.format("Unable to get text of webelement %s", locator));
			return String.format("Unable to get text of webelement %s", locator);

		}
	}
	/*
	 *
	 * getPageTitle
	 *
	 */

	public String getPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception ex) {
			System.out.println("Unable to get page title");
			return "";
		}
	}

	/*
	 *
	 * get Text of locator
	 *
	 */
	public String getText(By locator) {
		try {
			return driver.findElement(locator).getText();
		} catch (Exception ex) {
			System.out.println(String.format("Unable to get %s page locator", locator));
			return "";
		}

	}

	/*
	 *
	 * Provide keys to star webelement using hashmap for rating purpose e.g I want
	 * to give 3 rating to webelement
	 *
	 * 3 will be mapped to webelement of 3rd star
	 *
	 */
	public HashMap<Integer, WebElement> mapStarWithKey(String value) {
		try {
			HashMap<Integer, WebElement> map = new HashMap<Integer, WebElement>();
			List<WebElement> elements = new ArrayList<WebElement>();

			List<String> string = getElementsWithUniquekey(value);
			for (String a : string) {
				elements.add(driver.findElement(By.xpath(a)));
			}
			Iterator<WebElement> iterator = elements.iterator();
			while (iterator.hasNext()) {
				for (int i = 1; i <= elements.size(); i++)
					map.put(i, iterator.next());
			}
			return map;
		} catch (Exception ex) {
			HashMap<Integer, WebElement> map1 = new HashMap<Integer, WebElement>();
			System.out.println(String.format("Unable to get %s xpath", value));
			map1.put(1, null);
			return map1;
		}

	}

	/*
	 *
	 * Mouse hover method
	 *
	 */
	public void mouseHover(WebElement web_Element_To_Be_Hovered) {
		try {
			Actions hover = new Actions(driver);
			hover.moveToElement(web_Element_To_Be_Hovered);

		} catch (Exception ex) {
			System.out.println(String.format("Unable to get %s Webelement", web_Element_To_Be_Hovered));
		}

	}

	/*
	 *
	 * clicking webelement star rating
	 *
	 */
	public void clickWebElementStar(WebElement web_Element_To_Be_Clicked) {
		try {
			web_Element_To_Be_Clicked.click();
		} catch (Exception ex) {
			System.out.println(String.format("Unable to get %s Webelement", web_Element_To_Be_Clicked));
		}

	}

	/*
	 *
	 * This method will return element when passed a key to know which star needs to
	 * be clicked
	 *
	 */
	public WebElement getValueThroughKey(int i, String xpath) {

		HashMap<Integer, WebElement> map = mapStarWithKey(xpath);
		return map.get(i);
	}

	/*
	 *
	 * This method is used to hover star and click which have two parameters integer
	 * which will let method know about star to be clicked
	 *
	 * and the string which will help us getting unique value of star
	 */
	public void HoverStarAndClick(int star, String xpath) {
		mouseHover(getValueThroughKey(star, xpath));
		clickWebElementStar(getValueThroughKey(star, xpath));

	}

	/*
	 *
	 * methods to get color of webElement star
	 *
	 */
	public String getColorOfWebElementStar(int star, String xpath) {
		String color = null;
		int i = 1;
		HashMap<Integer, WebElement> map = mapStarWithKey(xpath);
		mouseHover((WebElement) map.get(star));
		while (i <= star) {

			color = ((WebElement) map.get(star)).getCssValue("color");
			i++;
		}
		return color;
	}

	/*
	 *
	 * method to select dropdown value if select class is not implemented
	 *
	 */
	public void dropDownValueWithOutSelect(String value, By locator) {
		List<WebElement> myElements = driver.findElements(locator);
		for (WebElement element : myElements) {
			if (element.getText().equalsIgnoreCase(value)) {
				element.click();
			}

		}
	}

	/*
	 *
	 * method to help string xpath to have a unique value which will help us to map
	 * with key
	 *
	 */
	public List<String> getElementsWithUniquekey(String xpath) {
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		List<String> strings = new ArrayList<String>();
		for (int i = 1; i <= elements.size(); i++) {
			strings.add(xpath + "[" + Integer.toString(i) + "]");
		}
		return strings;
	}
	public void navigateBack(){
		driver.navigate().back();
	}

	public void clickOnFirstElementFromListOfElements(By locator){
		try{
			waitForElement(locator);
			List<WebElement> elements=driver.findElements(locator);
			elements.get(0).click();
		}
		catch (Exception ex){
			System.out.println(String.format("Element %s not clickable", locator));
		}

	}

	/*
	 * To check if the alert is present or not
	 *
	 *
	 * */
	public boolean isAlertPresent() throws IOException {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver,
				Integer.parseInt(config.readConfigData("explicitWait")));
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		return foundAlert;

	}

	public void acceptAlert() throws IOException {
		if(isAlertPresent()){
			driver.switchTo().alert().accept();
		}
	}

	public boolean isElementSelected(By locate) {

		try {
			waitForElement(locate);

			return driver.findElement(locate).isSelected();

		}

		catch (Exception ex) {

			System.out.println(String.format("Element - %s not found", locate));
			return false;
		}


	}

	public List<String> InnertextofIcons(By locator){
		List<WebElement> elements=driver.findElements(locator);
		List<String> lst=new ArrayList<>();
		try {
			for (WebElement element : elements) {
				lst.add(element.getAttribute("content-desc"));

			}
			return lst;
		}
		catch (Exception ex){
			System.out.println(String.format("Issue with List", ex.getMessage()));
			return lst;
		}
	}

	public boolean  compareString(List<String> strs, By locator){
		boolean flag=false;
		try{
			List<String> str_Elements=InnertextofIcons(locator);
			for(int i=0;i<strs.size();i++){
				if(str_Elements.get(i).equals(strs.get(i))){
					flag=true;
				}

			}
			return flag;

		}
		catch (Exception ex){
			System.out.println(String.format("Out of Index, issue with Compare String method", ex.getMessage()));
			return flag;
		}


	}

}
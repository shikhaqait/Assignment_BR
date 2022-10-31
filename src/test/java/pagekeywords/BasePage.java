package pagekeywords;

import logger.MainLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import webDriver.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage() {
        this.driver = DriverManager.getDriver();
        System.out.printf("driver is" + driver);
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * retrun the webelement
     *
     * @param locator
     * @return
     */
    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * retrun the webelement
     *
     * @param locator
     * @return
     */
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public List<String> getALLTextFromElements(List<WebElement> elements) {
        List<String> allTexts = new ArrayList<String>();
        for (WebElement el : elements) {
            allTexts.add(el.getText());
        }
        return allTexts;
    }

    public WebElement getElementBasedUponText(List<WebElement> elements, String expectedText) {
        List<String> allTexts = new ArrayList<String>();
        for (WebElement el : elements) {
            if (el.getText().equals(expectedText))
                return el;
        }
        return null;
    }

    public WebElement getElementBasedUponTextContains(List<WebElement> elements, String expectedText) {
        List<String> allTexts = new ArrayList<String>();
        for (WebElement el : elements) {
            MainLogger.logger().info("available menu item text is " + el.getText());
            if (el.getText().contains(expectedText))
                return el;
        }
        return null;
    }

    public List<WebElement> getElementsBasedUponTextContains(List<WebElement> elements, String expectedText) {
        List<WebElement> allTexts = new ArrayList<WebElement>();
        for (WebElement el : elements) {
            if (el.getText().contains(expectedText)) {
                allTexts.add(el);
            }
        }
        return allTexts;
    }

    /**
     * Method enter text in element
     *
     * @param element
     * @param text
     */
    public void enterText(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    /**
     * Click using js
     *
     * @param element
     */
    public void clickUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public void clickUsingAction(WebElement element) {
        Actions action = new Actions(this.driver);
        action.moveToElement(element);
        action.click().build().perform();
    }

    public void executeJSscript(String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }

    public void selectValueFromDropDown(WebElement el, String text) {
        Select sel = new Select(el);
        sel.selectByVisibleText(text);

    }

    public void launchApplication() {
        String domain = System.getProperty("url");
        if (domain == null) {
            domain = "http://localhost:8080";
            MainLogger.logger().info("\n");
            MainLogger.logger().info("Default environment local " + domain);
        }
        domain = domain + "/cms/";
        driver.get(domain);
        MainLogger.logger().info("Application launched " + domain);

    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            MainLogger.logger().info("Resuming after " + seconds + " seconds");
        }
    }

}

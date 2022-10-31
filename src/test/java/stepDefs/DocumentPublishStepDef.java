package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagekeywords.LoginPage;
import pagekeywords.MainPage;
import logger.MainLogger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import webDriver.DriverManager;


public class DocumentPublishStepDef {

    private LoginPage loginPage;
    private MainPage mainPage;

    @And("I launch application")
    public void launchApplicationStep() {
        DriverManager.setDriver();
        loginPage = new LoginPage();
        loginPage.launchApplication();
    }

    @Given("login using {string} credentials")
    public void login_using_credentials(String userName) {
        loginPage.enterUsername(userName);
        loginPage.enterPassword(userName);
        loginPage.clickSignInbtn();
        MainLogger.logger().info("signed in into the application");
    }

    @And("I click on Content tab")
    public void clickOntab() {
        mainPage = new MainPage();
        mainPage.clickContentTab();
        MainLogger.logger().info("Clicked on content tab");
    }

    @When("I add {string} type new document in my existing project")
    public void createDocument(String documentType) {
        mainPage.clickOnAddNewDocumentType();
        mainPage.createNewDocument("Resource Bundle");
    }

    @And("Add {string} {string} and {string} in document")
    public void enterDetailsinDocumet(String key, String value, String description) {
        mainPage.addManageValueSet(key, value, description);
    }

    @And("I give 'ID' value")
    public void giveId() {
        mainPage.enterId("test" + System.nanoTime());
    }

    @And("I click 'Save & Close'")
    public void clickOnSaveAndClose() {
        mainPage.saveAndClose();
    }

    @Then("New document get created in 'Offline' mode with small red color tick mark on top of the document")
    public void newDocumentGetCreated() {
        Assert.assertTrue(mainPage.getALLTextFromElements(mainPage.getListOfCreatedDocument()).contains(mainPage.documentName));
        WebElement documentSection = mainPage.getElementBasedUponText(mainPage.getListOfCreatedDocument(), mainPage.documentName);
        String status = mainPage.getCreatedDocumentStatus(documentSection);
        Assert.assertEquals(status, "Offline");

    }

    @When("I click on Publication drop-down")
    public void clickOnPublish() {
        mainPage.waitForDocumentPageId();
        mainPage.getListOfCreatedDocument();
        mainPage.selectTheMenuItem("Publication");
    }

    @When("I select {string} option")
    public void selectTheSubMenuItemFromPublish(String subMenu) {
        mainPage.selectTheSubMenuFromPublishDropDown(subMenu);
    }

    @And("I select an unpublished document")
    public void selectAnUnpublishedDocument() {
        mainPage.selectProjectFolder();
        boolean isDocPresent = mainPage.selectTheUnpublishedDocument();
        if (!isDocPresent) {
            Assert.fail("No unpublished document present with pattern " + mainPage.documentNamePattern);
        }
    }

    @Then("small tick mark on top of the document changes to green")
    public void verifyDocumentGetPublished() {
        WebElement documentSection = mainPage.getElementBasedUponText(mainPage.getListOfCreatedDocument(), mainPage.documentName);
        String status = mainPage.getCreatedDocumentStatus(documentSection);
        Assert.assertEquals(status, "Live");
        MainLogger.logger().info("Document " + mainPage.documentName + " has status Live");
    }


}

package pagekeywords;

import logger.MainLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.MainPageObject;

import java.util.List;

public class MainPage extends BasePage {


    public String documentNamePattern = "Auto_Document";
    public String documentName;
    private MainPageObject mainPageObject;

    public MainPage() {
        mainPageObject = new MainPageObject();
    }

    public void clickContentTab() {
        clickElement(getElement(mainPageObject.a_contentTab));
    }

    public void saveAndClose() {
        clickElement(getElement(mainPageObject.linkSaveAndClose));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(mainPageObject.inputDocumentID));
    }

    public void enterId(String id) {
        wait.until(ExpectedConditions.elementToBeClickable(getElement(mainPageObject.inputDocumentID)));
        enterText(getElement(mainPageObject.inputDocumentID), id);
        MainLogger.logger().info("enter document id " + id);
    }

    public void addManageValueSet(String key, String value, String description) {
        getListOfCreatedDocument();
        wait.until(ExpectedConditions.elementToBeClickable(getElement(mainPageObject.iconAdd)));
        clickUsingJS(getElement(mainPageObject.iconAdd));
        enterText(getElement(mainPageObject.inputKey), key);
        enterText(getElement(mainPageObject.inputValue), value);
        enterText(getElement(mainPageObject.inputDesc), description);
        clickElement(getElement(mainPageObject.buttonOk));
        MainLogger.logger().info("added manage value set of document " + key + " ," + value + ", " + description);
    }

    public List<WebElement> getListOfCreatedDocument() {
        return getElements(mainPageObject.listOfDocuments);
    }

    public void clickOnAddNewDocumentType() {
        clickUsingJS(getElement(mainPageObject.svg_Projectdropdown));
        MainLogger.logger().info("click on add new doc type");
    }

    public String createNewDocument(String resourceType) {
        documentName = documentNamePattern + System.currentTimeMillis();
        clickElement(getElement(mainPageObject.span_newDocument));
        enterText(getElement(mainPageObject.input_name), documentName);
        selectValueFromDropDown(getElement(mainPageObject.dd_documentType), resourceType);
        clickUsingJS(getElement(mainPageObject.btn_submit));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(mainPageObject.btn_submit));
        MainLogger.logger().info("created a new doc with name " + documentName);
        return documentName;
    }

    public String getCreatedDocumentStatus(WebElement element) {
        return element.findElement(mainPageObject.iconDocumentState).getAttribute("title");

    }

    public void selectProjectFolder() {
        clickElement(getElement(mainPageObject.projectFolderList));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(mainPageObject.iconAdd));
    }

    public boolean selectTheUnpublishedDocument() {
        boolean flag = false;
        List<WebElement> docs = getElementsBasedUponTextContains(getListOfCreatedDocument(), documentNamePattern);
        for (WebElement doc : docs) {
            MainLogger.logger().info("Text is*********** " + doc.getText());
            if (getCreatedDocumentStatus(doc).contains("Offline")) {
                documentName = doc.findElement(mainPageObject.documentName).getText().trim();
                MainLogger.logger().info("User selected document " + documentName);
                clickElement(doc);
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void selectTheSubMenuFromPublishDropDown(String subMenu) {
        clickElement(getElementBasedUponText(getElements(mainPageObject.subMenuItems), subMenu));
        MainLogger.logger().info("selected a sub menu " + subMenu);
        sleep(2);
    }

    public void selectTheMenuItem(String menu) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(mainPageObject.menuItemList, 3));
        clickElement(getElementBasedUponTextContains(getElements(mainPageObject.menuItemList), menu));
        MainLogger.logger().info("selected a  menu item " + menu);
    }

    public void waitForDocumentPageId() {
        wait.until(ExpectedConditions.elementToBeClickable(getElement(mainPageObject.documentIdName)));
    }


    //
}

package pageobjects;

import org.openqa.selenium.By;

public class MainPageObject {
    public By a_contentTab = By.cssSelector("[title='Content']");
    public By svg_Projectdropdown = By.cssSelector(".hippo-tree-dropdown-icon-container");
    public By span_newDocument = By.cssSelector("[title='Add new document...']");
    public By input_name = By.cssSelector(".hippo-dialog-body .input [type='text']");
    public By dd_documentType = By.cssSelector(".hippo-dialog-body select[name='prototype']");
    public By btn_submit = By.cssSelector(".hippo-dialog-bottom [type='submit']");

    private String tabPanel = ".tabpanel [style='display: block;']";

    public By iconAdd = By.cssSelector(tabPanel + " .resourcebundle-plugin [title=\"Add\"]");

    public By inputKey = By.cssSelector("input[name=\"key-value\"]");

    public By inputValue = By.cssSelector("input[name*=\"set-value\"]");

    public By inputDesc = By.cssSelector("textarea[name=\"desc-value\"]");

    public By buttonOk = By.cssSelector("input[value=\"OK\"]");

    public By inputDocumentID = By.cssSelector(tabPanel + " .hippo-property-field-mandatory input");

    public By listOfDocuments = By.cssSelector(".hippo-list-documents tbody tr");

    public By documentName = By.cssSelector(".hippo-document");

    public By linkSaveAndClose = By.cssSelector(tabPanel + " [title='Save & Close']");

    public By documentIdName = By.cssSelector(tabPanel + " .hippo-editor-field-value-container");

    public By iconDocumentState = By.cssSelector("[class*='state']");


    public By projectFolderList = By.cssSelector(".doclisting-name.link span[id]");

    public By menuItemList = By.cssSelector(tabPanel + " .menu-item");

    public By subMenuItems = By.cssSelector(".hippo-toolbar-menu-item-button");
}

package pagekeywords;

import pageobjects.LoginPageObject;

public class LoginPage extends BasePage {

    private LoginPageObject loginPageObj;

    public LoginPage() {
        loginPageObj = new LoginPageObject();
    }

    public void enterUsername(String username) {
        enterText(getElement(loginPageObj.input_username), username);
    }

    public void enterPassword(String password) {
        enterText(getElement(loginPageObj.input_password), password);
    }

    public void clickSignInbtn() {
        clickElement(getElement(loginPageObj.btn_login));
    }
}

package pageobjects;

import org.openqa.selenium.By;

public class LoginPageObject {

    public By input_username = By.cssSelector(".login-form-input-username");
    public By input_password = By.cssSelector(".login-form-input-password");
    public By btn_login = By.cssSelector(".login-form-submit");

}

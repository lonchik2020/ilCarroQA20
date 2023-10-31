package manager;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    By btnLoginNavigatorMenu = By.xpath("//a[contains(@href, '/login')]");
    By inputEmailLoginForm = By.xpath("//input[@id='email']");
    By inputPasswordLoginForm = By.xpath("//input[@id='password']");
    By btnYallaLoginForm = By.xpath("//button[@type='submit']");
    By textSuccessLoginPopUp = By.xpath("//h2[@class='message']");
    By btnOpenRegForm = By.xpath("//a[contains(@href, '/registration')]");
    By inputNameReg = By.xpath("//input[@id='name']");
    By inputLastNameReg = By.xpath("//input[@id='lastName']");
    By inputEmailReg = By.xpath("//input[@id='email']");
    By inputPasswordReg = By.xpath("//input[@id='password']");
    String btnRegNewUser = "document.querySelector('#terms-of-use').click();";
    String btnOkPopUpStr = "document.querySelector('[type='button']').click();";
    By checkBoxReg = By.xpath("//label[@for='terms-of-use']");
    By btnYallaReg = By.xpath("//button[@type='submit']");
    By textPopUpSuccessRegH1 = By.xpath("//div[@class='dialog-container']//h1[@class='title']");
    By btnLogout = By.xpath("//a[contains(@href, 'logout')]");
    By btnOkPopUp = By.xpath("//button[@type='button']");
    By errorMessageWrongEmailReg = By.xpath("//input[@autocomplete='email']/..//div//div");
    By errorMessageIncorrectPasswordReg = By.xpath("//input[@autocomplete='new-password']/..//div//div");



    public void login(UserDTO userDTO) {
        clickBase(btnLoginNavigatorMenu);
        typeTextBase(inputEmailLoginForm, userDTO.getEmail());//locator of email + text
        typeTextBase(inputPasswordLoginForm, userDTO.getPassword());//locator of password + text
        clickBase(btnYallaLoginForm);
    }

    public void login(UserDTOWith userDTO) {
        clickBase(btnLoginNavigatorMenu);
        typeTextBase(inputEmailLoginForm, userDTO.getEmail());//locator of email + text
        typeTextBase(inputPasswordLoginForm, userDTO.getPassword());//locator of password + text
        clickBase(btnYallaLoginForm);
    }

    public void loginUserDtoLombok(UserDTOLombok user) {
        clickBase(btnLoginNavigatorMenu);
        typeTextBase(inputEmailLoginForm, user.getEmail());//locator of email + text
        typeTextBase(inputPasswordLoginForm, user.getPassword());//locator of password + text
        clickBase(btnYallaLoginForm);
    }

    public boolean validatePopUpMessageSuccessAfterLogin() {
        return isTextEqual(textSuccessLoginPopUp, "Logged in success");

    }


    public boolean validatePopUpMessageLoginIncorrect() {
        return isTextEqual(textSuccessLoginPopUp, "\"Login or Password incorrect\"");
    }



    public void fillRegistrationForm(UserDTOLombok user) {
        clickBase(btnOpenRegForm);
        typeTextBase(inputNameReg, user.getName());
        typeTextBase(inputLastNameReg, user.getLastName());
        typeTextBase(inputEmailReg, user.getEmail());
        typeTextBase(inputPasswordReg, user.getPassword());
        //clickByXY(checkBoxReg);
        clickByXY(checkBoxReg,10,12);
        //jsClickBase(btnRegNewUser);
        clickBase(btnYallaReg);


    }

    public boolean validatePopUpMessageSuccessAfterRegistration() {
        String expectedResult = "Registered".toUpperCase();
        return isTextEqual(textPopUpSuccessRegH1, expectedResult);

    }

    public boolean btnLogoutExist() {
        return isElementExist(btnLogout);
    }

    public void logout() {
        clickBase(btnLogout);
    }

    public void clickOkPopUpSuccessLogin() {
        //jsClickBase(btnOkPopUpStr);
        //clickByXY(btnOkPopUp, 0.5, 2);
        clickBase(textPopUpSuccessRegH1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Actions actions = new Actions(driver);
        //Use the sendKeys method to stimulate pressing the "Enter" key on the active element
        actions.sendKeys(Keys.ESCAPE).perform();

        //typeTextBase(textPopUpSuccessRegH1, String.valueOf(Keys.ESCAPE));

    }

    public boolean validateMessageIncorrectEmail() {
        return isTextEqual(errorMessageWrongEmailReg, "Wrong email format");

    }

    public boolean validateMessageWrongPassword() {
        return isTextEqual(errorMessageIncorrectPasswordReg, "PASSWORD MUST CONTAIN 1 UPPERCASE LETTER, 1 LOWERCASE LETTER, 1 NUMBER AND ONE SPECIAL SYMBOL OF [@$#^&*!]");
    }

    public boolean validateErrorEmptyEmailReg() {
        return isTextEqual(errorMessageWrongEmailReg, "Email is required");
    }
}
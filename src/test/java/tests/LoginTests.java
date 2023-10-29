package tests;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @BeforeTest
    public void preconditionsLogin(){
   logoutIfLogin();
        // user login
        // user open web not login

    }

    @AfterTest
    public void postconditionsLogin(){
        app.getUserHelper().clickOkPopUpSuccessLogin();
    }

    @Test
    public void positiveLoginUserDTO(){
        //create user for testing
        UserDTO userDTO = new UserDTO("lonchik_7_7@walla.co.il", "Samimi@44@");
        //transfer the user inside the login method
        app.getUserHelper().login(userDTO);
        //asking for validation and expecting to get true
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test
    public void positiveLoginUserDTOWith(){
        //create user for testing
        UserDTOWith userDTOWith = new UserDTOWith().withEmail("lonchik_7_7@walla.co.il")
                .withPassword("Samimi@44@");
        //transfer the user inside the login method
        app.getUserHelper().login(userDTOWith);
        //asking for validation and expecting to get true
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test
    public void positiveLogin(){
        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email("lonchik_7_7@walla.co.il")
                .password("Samimi@44@").build();
        app.getUserHelper().loginUserDtoLombok(userDTOLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }


    @Test
    public void negativePasswordWithoutSymbol(){
        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email("lonchik_7_7@walla.co.il")
                .password("Samimi@444").build();
        app.getUserHelper().loginUserDtoLombok(userDTOLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test
    public void negativePasswordWithoutNumbers(){
        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email("lonchik_7_7@walla.co.il")
                .password("Samimi@era").build();
        app.getUserHelper().loginUserDtoLombok(userDTOLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test
    public void negativePasswordWithoutLetters(){
        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email("lonchik_7_7@walla.co.il")
                .password("243567@145").build();
        app.getUserHelper().loginUserDtoLombok(userDTOLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

}

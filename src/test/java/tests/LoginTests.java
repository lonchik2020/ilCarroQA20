package tests;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest{

    @BeforeMethod
    public void preconditionsLogin(){
        // app.getUserHelper().refreshPage();
        //  app.navigateToMainPage();
        logoutIfLogin();
        // user login
        // user open web not login
    }

    @AfterMethod
    public void postconditionsLogin(){
        app.getUserHelper().clickOkPopUpSuccessLogin();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
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
        app.getUserHelper().loginUserDtoLombok(userDTOLombok);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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

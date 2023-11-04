package tests;

import dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTests extends BaseTest{

    @BeforeTest
    public void preconditionsLogin(){
//       //app.navigateToMainPage();
        logoutIfLogin();
        // user login
        // user open web not login

    }

  // @AfterTest
  //  public void postconditionsLogin(){
  //      app.getUserHelper().clickOkPopUpSuccessLogin();
   //     try {
 //           Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
   // }


    @Test
    public void positiveRegistration(){
        String email = randomUtils.generateEmail(7);

        UserDTOLombok user = UserDTOLombok.builder()
                .email(email)
                .password("Samimi@44@")
                .lastName("abcde")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
    }

    @Test
    public void negativeRegistrationWrongEmail(){
        String email = randomUtils.generateEmail(7);

        UserDTOLombok user = UserDTOLombok.builder()
                .email("abc@")
                .password("Samimi@44@")
                .lastName("abcde")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateMessageIncorrectEmail());
    }

    @Test
    public void negativeRegistrationWrongPassword(){//
        String email = randomUtils.generateEmail(7);

        UserDTOLombok user = UserDTOLombok.builder()
                .email(email)
                .password("Samimi44")
                .lastName("abcde")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateMessageWrongPassword());
    }

    @Test
    public void negativeRegistrationBlankEmail(){
        //String email = randomUtils.generateEmail(7);//no need to generate email in this case
        UserDTOLombok user = UserDTOLombok.builder()
                .email("")
                .password("Samimi@44@")
                .lastName("abcde")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateErrorEmptyEmailReg());
    }
}

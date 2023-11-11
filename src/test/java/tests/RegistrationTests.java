package tests;

import dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTests extends BaseTest{

    @AfterMethod(alwaysRun = true)
    public void postConditionsBeforeRegMethod(){
        if(flagOfPopUpMessage) {
            flagOfPopUpMessage = false;
            app.getUserHelper().clickOkPopUpSuccessLogin();
        }
        if(flagOfSuccessLogin){
            flagOfSuccessLogin = false;
            app.getUserHelper().logout();
        }
    }


    @Test(groups={"smoke","regression"})
    public void positiveRegistration(){
        app.getUserHelper().refreshPage();
        String email = randomUtils.generateEmail(7);

        UserDTOLombok user = UserDTOLombok.builder()
                .email(email)
                .password("Samimi@44@")
                .lastName("abcde")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        //flagOfSuccessLogin = true;
        //flagOfPopUpMessage = true;


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        flagOfSuccessLogin = true;
        flagOfPopUpMessage = true;

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
        //flagOfPopUpMessage = true;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(app.getUserHelper().validateMessageIncorrectEmailReg());
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
        //flagOfPopUpMessage = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(app.getUserHelper().validateMessageWrongPasswordReg());
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
        //flagOfPopUpMessage = true;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(app.getUserHelper().validateErrorEmptyEmailReg());
    }
}

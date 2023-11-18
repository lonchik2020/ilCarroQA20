package tests;

import data.DataProviderLogin;
import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest{


   // @BeforeClass
   // public void preConditionsBeforeClass(){
     //   app.navigateToMainPage();
    //    app.getUserHelper().refreshPage();
     //   app.getUserHelper().openLoginPage();
//}

    @AfterMethod(alwaysRun = true)
    public void postConditionsLoginMethod(){
        if(flagOfPopUpMessage) {
            flagOfPopUpMessage = false;
            app.getUserHelper().clickOkPopUpSuccessLogin();
        }
        if(flagOfSuccessLogin){
            flagOfSuccessLogin = false;
            app.getUserHelper().logout();
        }

   }


    @Test(groups={"smoke"})
    public void positiveLoginUserDTO(){
        //app.getUserHelper().refreshPage();
        UserDTO userDTO = new UserDTO("lonchik_7_7@walla.co.il", "Samimi@44@");
        logger.info("logger info - start test positiveLoginUserDTO");
//        logger.info(String
//                .format("in the next function: fill email input with email: %s and with the password: %s and click on button login",
//                        userDTO.getEmail(), userDTO.getPassword()));
        app.getUserHelper().login(userDTO);
        flagOfSuccessLogin = true;
        flagOfPopUpMessage = true;

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        logger.info("assertTrue validation");
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
        //app.getUserHelper().clickOkPopUpSuccessLogin();
    }

    @Test(groups={"regression"})
    public void positiveLoginUserDTOWith(){
        UserDTOWith userDTOWith = new UserDTOWith().withEmail("lonchik_7_7@walla.co.il")
                .withPassword("Samimi@44@");
        logger.info("logger info - start test positiveLoginUserDTOWith ");
        app.getUserHelper().login(userDTOWith);
        flagOfSuccessLogin = true;
        flagOfPopUpMessage = true;
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test(dataProvider = "positiveDataLogin", dataProviderClass = DataProviderLogin.class)
    public void positiveLoginUserDTOLombok(Method method, UserDTOLombok userDP){
        app.getUserHelper().refreshPage();
        long timeStart, timeFinish;

//        UserDTOLombok user = UserDTOLombok.builder()
//                .email("lonchik_7_7@walla.co.il")
//                .password("Samimi@44@")
//                .build();

        timeStart = System.currentTimeMillis();
        logger.info("logger info - start test positiveLoginUserDTOLombok ----> " + method.getName());
        logger.info("Test date  ----> " + userDP.toString());

        app.getUserHelper().loginUserDtoLombok(userDP);
//        try {
//            Thread.sleep(1000);
//       } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        flagOfSuccessLogin = true;
        flagOfPopUpMessage = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
        timeFinish = System.currentTimeMillis();
        logger.info("Method finish ----> " + method.getName() + "method duration" + (timeFinish - timeStart));
    }



    @Test(dataProvider = "loginCSV", dataProviderClass = DataProviderLogin.class, groups={"smoke"})
    public void positiveLoginUserDTOLombok2(Method method, UserDTOLombok user){
        app.getUserHelper().refreshPage();
        long timeStart, timeFinish;

//        UserDTOLombok user = UserDTOLombok.builder()
//                .email("lonchik_7_7@walla.co.il")
//                .password("Samimi@44@")
//                .build();

        timeStart = System.currentTimeMillis();
        logger.info("logger info - start test positiveLoginUserDTOLombok ----> " + method.getName());
        logger.info("Test date  ----> " + user.toString());

        app.getUserHelper().loginUserDtoLombok(user);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        flagOfSuccessLogin = true;
        flagOfPopUpMessage = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
        timeFinish = System.currentTimeMillis();
        logger.info("Method finish ----> " + method.getName() + "method duration" + (timeFinish - timeStart));
    }




    @Test(groups={"smoke"})
    public void negativeLoginPasswordWithoutSymbol() {
        UserDTOLombok user = UserDTOLombok.builder()
                .email("lonchik_7_7@walla.co.il")
                .password("Samimi444").build();
        app.getUserHelper().loginUserDtoLombok(user);
        flagOfPopUpMessage = true;
                try {
            Thread.sleep(1000);
       } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(app.getUserHelper().validateMessageAlertLoginOrPasswordIncorrect());
    }

    @Test(invocationCount = 2, dataProvider = "negativePasswordDataLogin", dataProviderClass = DataProviderLogin.class)
    public void negativeLoginPasswordWithoutNumbers(UserDTOLombok userDP){
//        UserDTOLombok user = UserDTOLombok.builder()
//                .email("lonchik_7_7@walla.co.il")
//                .password("Samimi@era").build();
        app.getUserHelper().loginUserDtoLombok(userDP);
        flagOfPopUpMessage = true;
                try {
            Thread.sleep(1000);
       } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(app.getUserHelper().validateMessageAlertLoginOrPasswordIncorrect());
    }

    @Test
    public void negativeLoginPasswordWithoutLetters(){
        UserDTOLombok user = UserDTOLombok.builder()
                .email("lonchik_7_7@walla.co.il")
                .password("243567@145").build();
        app.getUserHelper().loginUserDtoLombok(user);
        flagOfPopUpMessage = true;
                try {
            Thread.sleep(1000);
       } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(app.getUserHelper().validateMessageAlertLoginOrPasswordIncorrect());
    }

}

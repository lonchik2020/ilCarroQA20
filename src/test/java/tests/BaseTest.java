package tests;

import dto.UserDTOLombok;
import manager.ApplicationManager;

import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import utils.RandomUtils;

import java.lang.reflect.Method;
import java.util.Arrays;


@Listeners(TestNGListener.class)
public class BaseTest {


    Logger logger = LoggerFactory.getLogger(BaseTest.class);//creation of Logger
    static ApplicationManager app = new ApplicationManager();
    //static - to create this object before start of project
    RandomUtils randomUtils = new RandomUtils();

    boolean flagOfSuccessLogin = false , flagOfPopUpMessage = false;

    UserDTOLombok user = UserDTOLombok.builder()
            .email("lonchik_7_7@walla.co.il")
            .password("Samimi@44@").build();


    @BeforeSuite(alwaysRun = true)
    public  void setup(){app.init();}

    @AfterSuite(alwaysRun = true)
    public void stop(){app.tearDown();}



    @BeforeMethod(alwaysRun = true)
    public void loggerBeforeMethod(Method method){
        logger.info("---------------------------------------------------------------------------");
        logger.info("start method: " + method.getName());
        logger.info("started method with params: " + Arrays.toString(method.getParameters()));
    }

    @AfterMethod(alwaysRun = true)
    public void loggerAfterMethod(Method method){
        logger.info("stop method: " + method.getName());
        logger.info("stopped method with params: " + Arrays.toString(method.getParameters()));
    }


    public void logoutIfLogin(){
        if(app.getUserHelper().btnLogoutExist()){
            app.getUserHelper().logout();
        }
    }


}

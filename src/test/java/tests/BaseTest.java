package tests;

import dto.UserDTOLombok;
import manager.ApplicationManager;
import manager.BaseHelper;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.RandomUtils;

import java.lang.reflect.Method;


@Listeners(TestNGListener.class)
public class BaseTest {

    Logger logger = LoggerFactory.getLogger(BaseTest.class);//creation of Logger
    static ApplicationManager app = new ApplicationManager();
    //static - to create this object before start of project
    RandomUtils randomUtils = new RandomUtils();

    UserDTOLombok userDTOLombok = UserDTOLombok.builder()
            .email("lonchik_7_7@walla.co.il")
            .password("Samimi@44@").build();


    @BeforeSuite(alwaysRun = true)
    public  void setup(){app.init();}

    @AfterSuite(alwaysRun = true)//each time the browser will close
    public void stop(){app.tearDown();}


    public void logoutIfLogin(){
        if(app.getUserHelper().btnLogoutExist()){
            app.getUserHelper().logout();
        }
    }

    @BeforeMethod
    public void loggerBe4Method(Method method){
        logger.info("start method: " + method.getName());
    }

    @BeforeMethod
    public void loggerAfterMethod(Method method){
        logger.info("stop method: " + method.getName());
    }

}

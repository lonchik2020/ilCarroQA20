package tests;

import dto.UserDTOLombok;
import manager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.RandomUtils;

public class BaseTest {
    static ApplicationManager app = new ApplicationManager();
    //static - to create this object before start of project
    RandomUtils randomUtils = new RandomUtils();

    UserDTOLombok userDTOLombok = UserDTOLombok.builder()
            .email("lonchik_7_7@walla.co.il")
            .password("Samimi@44@").build();


    @BeforeSuite
    public  void setup(){app.init();}

    @AfterSuite
    public void stop(){app.tearDown();}


    public void logoutIfLogin(){
        if(app.getUserHelper().btnLogoutExist()){
            app.getUserHelper().logout();
        }
    }

}

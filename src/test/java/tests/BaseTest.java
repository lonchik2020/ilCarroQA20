package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.RandomUtils;

public class BaseTest {
    static ApplicationManager app = new ApplicationManager();
    //static - to create this object before start of project
    RandomUtils randomUtils = new RandomUtils();

    @BeforeSuite
    public  void setup(){
        app.init();
    }

    @AfterSuite
    public void stop(){
        app.tearDown();
    }

}

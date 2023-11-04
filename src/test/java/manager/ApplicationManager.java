package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);//creation of Logger

    WebDriver driver;
    UserHelper userHelper;//1



    public void init(){
        driver = new ChromeDriver();
        driver.navigate().to("https://ilcarro.web.app/search");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        userHelper = new UserHelper(driver);//2
        logger.info("navigated to the url: https://ilcarro.web.app/search");//instruction for Logger
    }

    public void navigateToMainPage(){
        driver.navigate().to("https://ilcarro.web.app/search");
    }

    public UserHelper getUserHelper() {//3
        return userHelper;
    }

    public void tearDown(){driver.quit();}



}

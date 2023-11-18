package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigProperties;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);//creation of Logger

    static String browser;

    EventFiringWebDriver driver;//object for connection between listener and web driver
    UserHelper userHelper;//1

    CarHelper carHelper;//**1

    public ApplicationManager(){//constructor with default browser
        browser = System.getProperty("browser", BrowserType.CHROME);
    }

    public void init(){
        //driver = new ChromeDriver();
        //driver = new EventFiringWebDriver(new ChromeDriver());

        //mini fabrice

        if(browser.equals(BrowserType.CHROME)) {
            driver = new EventFiringWebDriver(new ChromeDriver());
            logger.info("created chrome browser");
        }else if(browser.equals(BrowserType.FIREFOX)){
            driver = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("started tests in firefox driver");
        }


        driver.navigate().to(ConfigProperties.getProperty("url"));
        logger.info("navigated to the url:" + ConfigProperties.getProperty("url"));//instruction for Logger
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.register(new WDListener());

        userHelper = new UserHelper(driver);//2
        carHelper = new CarHelper(driver);//**2
    }


    public UserHelper getUserHelper() {//3
        return userHelper;
    }

    public CarHelper getCarHelper(){//**3
        return carHelper;
    }


    public EventFiringWebDriver getDriver(){
        if(driver == null){
            init();
        }
        return driver;
    }



    public void tearDown(){
        driver.quit();
        logger.info("Stop testing---> " + LocalDateTime.now());
    }


    public void navigateToMainPage(){
        driver.navigate().to(ConfigProperties.getProperty("url"));
    }

    public boolean isThePageIsHomePage(){
        String url = driver.getCurrentUrl();
        System.out.println(url + " -------> url");
        return url.equals(ConfigProperties.getProperty("url")+ " Start testing");

    }

}

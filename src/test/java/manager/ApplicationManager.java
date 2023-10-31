package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver driver;
    UserHelper userHelper;//1



    public void init(){
        driver = new ChromeDriver();
        driver.navigate().to("https://ilcarro.web.app/search");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        userHelper = new UserHelper(driver);//2
    }

    public void navigateToMainPage(){
        driver.navigate().to("https://ilcarro.web.app/search");
    }

    public UserHelper getUserHelper() {//3
        return userHelper;
    }

    public void tearDown(){driver.quit();}



}

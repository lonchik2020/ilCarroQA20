package starttesting;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RandomUtils;

import java.util.concurrent.TimeUnit;

public class RegistrationTest {

    WebDriver driver;
    RandomUtils randomUtils = new RandomUtils();

    @BeforeClass
    public void preConditions(){
        driver = new ChromeDriver();
        driver.navigate().to("https://ilcarro.web.app/search");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void registrationPositive(){
        String email = randomUtils.generateEmail(7);
        System.out.println(email);

        driver.findElement(By.xpath("//a[contains(@href, '/registration')]")).click();
        WebElement inputName = driver.findElement(By.xpath("//input[@id='name']"));
        inputName.click();
        inputName.clear();
        inputName.sendKeys("abc");


        //driver.findElement(By.xpath("//a[contains(@href, '/registration')]")).click();
        WebElement inputName2 = driver.findElement(By.xpath("//input[@id='lastName']"));
        inputName2.click();
        inputName2.clear();
        inputName2.sendKeys("abcd");



        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='email']"));
        inputEmail.click();
        inputEmail.clear();
        inputEmail.sendKeys(email);


        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys("Samimi@44@");


        //checkbox
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("document.querySelector('#terms-of-use').click();");

        WebElement termsOfUseCheckbox = driver.findElement(By.xpath("//input[@id='terms-of-use']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", termsOfUseCheckbox);

        //driver.findElement(By.xpath("//input[@id='terms-of-use']")).click();
        //driver.findElement(By.xpath("//label[@for='terms-of-use']")).click();


        //======================================================================================

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // //h1[@class='title']
        // Registered

        WebElement textMessagePopUpH1 = driver.findElement(By.xpath("//div[@class='dialog-container']//h1[@class='title']"));
        String textMessageH1 = textMessagePopUpH1.getText().trim().toUpperCase();
        String expectedResult = "Registered".toUpperCase();
        System.out.println(textMessageH1);

        Assert.assertEquals(textMessageH1,expectedResult);


    }

    @AfterClass
    public void postConditions(){
        driver.quit();
    }

}

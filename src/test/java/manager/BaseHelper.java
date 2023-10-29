package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class BaseHelper {

    WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement findElementBase(By locator){
        System.out.println(locator);
        return driver.findElement(locator);
    }

    private List<WebElement> findElementsBase(By locator){
        System.out.println(locator);
        return driver.findElements(locator);
    }

    public boolean isElementExist(By locator){
        return !findElementsBase(locator).isEmpty();
       // return findElementsBase(locator).size()>0;
    }


    public void clickBase(By locator){
        WebElement el = findElementBase(locator);
        el.click();
    }

    public String getTextBase(By locator){
        WebElement el = findElementBase(locator);
        return el.getText().trim().toUpperCase();
    }

    public void typeTextBase(By locator, String text){
        WebElement el = findElementBase(locator);
        el.click();
        el.clear();
        el.sendKeys(text);
    }

    public boolean isTextEqual(By locator, String expectedResult){
        String actualResult = getTextBase(locator);
        expectedResult = expectedResult.toUpperCase();

        if(expectedResult.equals(actualResult)){
            return true;
        }else{
            System.out.println("expected result: " + expectedResult + "actual result: " + actualResult);
            return false;
        }

    }

   public void jsClickBase(String locator){
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript(locator);
    }

    public void clickByXY(By locator, double down, int right){//down 10 , right 12
        //creating object of rectangle -- we transfer there locator and it writes information about the element
        Rectangle rect = findElementBase(locator).getRect();
        int x = rect.getX()+ (rect.getWidth()/ 8);
        int y = rect.getY()+ (rect.getHeight()/ 2);

        Actions actions = new Actions(driver);
        actions.moveByOffset(x,y).click().perform();


//        Rectangle rectangle = findElementBase(locator).getRect();
//        int x = rectangle.getX()+ (rectangle.getWidth()/right);
//        int y = rectangle.getY()+ (rectangle.getHeight()/down);
//
//        Actions actions = new Actions(driver);
//        actions.moveByOffset(x,y).click().perform();


    }
}

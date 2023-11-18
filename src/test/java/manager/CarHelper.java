package manager;

import dto.AddCarDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CarHelper extends BaseHelper {

    public CarHelper(WebDriver driver) {
        super(driver);
    }

    By btnAddNewCarMenuHeader = By.xpath("//a[contains(@href,'/let-car-work')]");
    By inputManufacture = By.xpath("//input[@id='make']");
    By inputModel = By.xpath("//input[@id='model']");
    By inputYear = By.id("year");
    By selectFuel = By.id("fuel");
    By inputSeats = By.id("seats");
    By inputClass = By.id("class");
    By inputSerialNumber = By.id("serialNumber");
    By inputPrice = By.id("price");
    By inputLocation = By.id("pickUpPlace");

    //By inputAbout = By.id("about");
    //By labelPhoto = By.id("photos");
    public By getOptionFuel(String str) {
        return By.xpath(String.format("//option[@value='%s']", str));
    }

    By btnSubmitNewCar = By.xpath("//button[@type='submit']");
    By textPopUpTitle = By.xpath("//div[@class='dialog-container']//h1[@class='title']");
    By btnAddAnotherCarPopUp = By.xpath("//button[contains(@class, 'negative-button')]");
    By containerCities = By.xpath("//div[contains(@class, 'pac-container pac-logo hdpi') and not(contains(@style, 'display: none'))]");
    private By locationInputLocator = By.xpath("//input[@id='pickUpPlace']");


    public void clickAddNewCar() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickBase(btnAddNewCarMenuHeader);
    }

    public void fillFormNewCar(AddCarDTO car) {
        enterLocationWithAutocomplete(car.getCity());
        typeTextBase(inputManufacture, car.getManufacture());
        typeTextBase(inputModel, car.getModel());
        typeTextBase(inputYear, String.valueOf(car.getYear()));//casting to string
        clickBase(selectFuel);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickBase(getOptionFuel(car.getFuel()));
        typeTextBase(inputSeats, String.valueOf(car.getSeats()));//casting to String
        typeTextBase(inputClass, car.getCarClass());
        typeTextBase(inputSerialNumber, car.getSerialNumber());
        typeTextBase(inputPrice, String.valueOf(car.getPricePerDay()));

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickBase(btnSubmitNewCar);
        // location

    }

    public void clickAddNewCarPopUp() {
        clickBase(btnAddAnotherCarPopUp);
    }

    public boolean validateMessagePopUp() {
        return isTextEqual(textPopUpTitle, "Car added".trim().toUpperCase());
    }

    private void enterLocationWithAutocomplete(String location) {
        typeTextBase(locationInputLocator, location);
        selectFirstOptionFromGooglePlacesDropdown();
    }

    private void selectFirstOptionFromGooglePlacesDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).toMillis());
        WebElement autoCompleteResult = wait.until(ExpectedConditions.visibilityOfElementLocated(containerCities));
        Actions builder = new Actions(driver);
        // builder.moveToElement(autoCompleteResult).perform();
        builder.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
    }

}

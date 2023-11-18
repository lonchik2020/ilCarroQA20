package tests;

import dto.AddCarDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CarTests extends BaseTest{
    @BeforeClass
    public void loginPreConditions(){
        app.navigateToMainPage();
        app.getUserHelper().loginUserDtoLombok(userDTOLombok);
        app.getUserHelper().clickOkPopUpSuccessLogin();
    }


    @Test
    public void addNewCarPositiveTest(){
        String serialNumber = randomUtils.generateStringDigits(14);
        AddCarDTO car = AddCarDTO.builder()
                .serialNumber(serialNumber)
                .manufacture("Dodge")
                .model("suzuki")
                .year(2003)
                .fuel("Gas")
                .seats(4)
                .carClass("444")
                .pricePerDay(240)
                .city("Haifa")
                .build();
        app.getCarHelper().clickAddNewCar();
        app.getCarHelper().fillFormNewCar(car);
        Assert.assertTrue(app.getCarHelper().validateMessagePopUp());

    }
    @AfterClass
    public void logoutPostConditions(){
        /*
        go to main page
        login
        close popup
         */
        app.getCarHelper().clickAddNewCarPopUp();
        app.getUserHelper().logout();
        app.navigateToMainPage();
        // app.getUserHelper().clickOkPopUpSuccessLogin();
    }

}

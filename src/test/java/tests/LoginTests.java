package tests;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

//    @Test
//    public void positiveLoginUserDTO(){
//        //create user for testing
//        UserDTO userDTO = new UserDTO("lonchik_7_7@walla.co.il", "Samimi@44@");
//        //transfer the user inside the login method
//        app.getUserHelper().login(userDTO);
//        //asking for validation and expecting to get true
//        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
//    }
//
//    @Test
//    public void positiveLoginUserDTOWith(){
//        //create user for testing
//        UserDTOWith userDTOWith = new UserDTOWith().withEmail("lonchik_7_7@walla.co.il")
//                .withPassword("Samimi@44@");
//        //transfer the user inside the login method
//        app.getUserHelper().login(userDTOWith);
//        //asking for validation and expecting to get true
//        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
//    }

    @Test
    public void positiveLogin(){
        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email("lonchik_7_7@walla.co.il")
                .password("Samimi@44@").build();
        app.getUserHelper().loginUserDtoLombok(userDTOLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

}

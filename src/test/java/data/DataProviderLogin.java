package data;

import dto.UserDTOLombok;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderLogin {

    @DataProvider
    public Iterator<Object[]> positiveDataLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDTOLombok.builder()
                        .email("lonchik_7_7@walla.co.il")
                        .password("Samimi@44@").build()
        });
        list.add(new Object[]{
                UserDTOLombok.builder()
                        .email("lonchik_7_7@walla.co.il")
                        .password("Samimi@44@").build()
        });
        return list.iterator();

    }

    @DataProvider
    public Iterator<Object[]> negativePasswordDataLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDTOLombok.builder()
                        .email("lonchik_7_7@walla.co.il")
                        .password("Samimi5445").build()
        });
        list.add(new Object[]{
                UserDTOLombok.builder()
                        .email("lonchik_7_7@walla.co.il")
                        .password("samimi@ss@").build()
        });
        return list.iterator();

    }

}

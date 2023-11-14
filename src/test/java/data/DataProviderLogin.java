package data;

import dto.UserDTOLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    @DataProvider
    public Iterator<Object[]>loginCSV(){
        List<Object[]> list = new ArrayList<>();
        String path = "src/test/resources/datalogin2.csv";
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(path)))){
            String line = reader.readLine();
            while( line !=null){
                String[]split = line.split(",");
                list.add(new Object[]{
                        UserDTOLombok.builder()
                                .email(split[0])
                                .password(split[1])
                                .build()
                });
                line = reader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return list.iterator();
    }

}

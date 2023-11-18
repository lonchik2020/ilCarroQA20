package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AddCarDTO {
    String serialNumber;
    String manufacture;
    String model;
    int year;
    String fuel;
    int seats;
    String carClass;
    int pricePerDay;
    String about;
    String city;
}



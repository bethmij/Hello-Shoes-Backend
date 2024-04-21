package lk.ijse.gdse66.HelloShoes.dto;

import lk.ijse.gdse66.HelloShoes.service.util.Gender;
import lk.ijse.gdse66.HelloShoes.service.util.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {

    private String customerCode;
    private String customerName;
    private Gender Gender;
    private Date loyaltyJoinedDate;
    private Level level;
    private int totalPoints;
    private Date dob;
    private String addressLine01;
    private String addressLine02;
    private String addressLine03;
    private String addressLine04;
    private String addressLine05;
    private String contactNo;
    private String email;
    private Timestamp recentPurchaseDate;

}

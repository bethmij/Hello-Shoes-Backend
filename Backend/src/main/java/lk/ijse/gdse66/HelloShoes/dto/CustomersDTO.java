package lk.ijse.gdse66.HelloShoes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Gender;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {

    @NotBlank(message = "customer code can not be null")
    @Pattern(regexp = "C00-[0-9]{3}", message = "customer code is not valid")
    private String customerCode;

    @NotBlank(message = "customer name can not be null")
    @Pattern(regexp = "[A-Za-z ]+", message = "name is not valid")
    private String customerName;

    private Gender gender;

    @JsonFormat(pattern = "yyyyMMdd")
    private Date loyaltyJoinedDate;
    private Level level;
    private int totalPoints;

    @JsonFormat(pattern = "yyyyMMdd")
    private Date dob;
    private String addressLine01;
    private String addressLine02;

    @NotBlank(message = "city code can not be null")
    private String addressLine03;
    private String addressLine04;

    @NotBlank(message = "postal  code can not be null")
    @Pattern(regexp = "\\d+", message = "postal code is not valid")
    private String addressLine05;

    @NotBlank(message = "contact no can not be null")
    @Pattern(regexp = "\\d{10}", message = "Contact  must be exactly 10 digits")
    private int contactNo;


    private String email;


}

package lk.ijse.gdse66.HelloShoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Gender;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Role;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "employee code can not be null")
    @Pattern(regexp = "EP00-[0-9]{3}", message = "employee code is not valid")
    private String employeeCode;

    @NotBlank(message = "employee name can not be null")
    @Pattern(regexp = "[A-Za-z ]+", message = "name is not valid")
    private String employeeName;

    private String profilePic;
    private Gender gender;
    private Status status;

    @NotBlank(message = "designation can not be null")
    private String designation;

    @NotNull(message = "designation code can not be null")
    private Role accessRole;
    private Date dob;
    private Date dateJointed;

    @NotNull(message = "attachedBranch code can not be null")
    private String attachedBranch;

    private String addressLine01;
    private String addressLine02;

    @NotBlank(message = "addressLine03 code can not be null")
    private String addressLine03;

    private String addressLine04;

    @NotBlank(message = "addressLine05 code can not be null")
    private String addressLine05;

    @NotBlank(message = "contactNo code can not be null")
    private String contactNo;

    @NotBlank(message = "emergencyContact code can not be null")
    private String emergencyContact;

    @NotBlank(message = "email code can not be null")
    private String email;
    private String guardian;

}

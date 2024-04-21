package lk.ijse.gdse66.HelloShoes.entity;

import lk.ijse.gdse66.HelloShoes.service.util.enums.Role;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Gender;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String employeeCode;
    private String employeeName;
    private String profilePic;
    private Gender gender;
    private Status status;
    private String designation;
    private Role accessRole;
    private Date dob;
    private Date dateJointed;
    private String attachedBranch;
    private String AddressLine01;
    private String AddressLine02;
    private String AddressLine03;
    private String AddressLine04;
    private String AddressLine05;
    private String contactNo;
    private String emergencyContact;
    private String email;
    private String guardian;

}

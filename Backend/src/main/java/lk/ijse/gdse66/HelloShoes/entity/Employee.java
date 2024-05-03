package lk.ijse.gdse66.HelloShoes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "employee_name", unique = true, nullable = false)
    private String employeeName;

    @Column(name = "profile_pic", columnDefinition = "LONGTEXT")
    private String profilePic;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private String designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_role", nullable = false)
    private Role accessRole;

    @Column (columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyyMMdd")
    private Date dob;

    @Column (columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyyMMdd")
    private Date dateJointed;

    @Column(name = "attached_branch", nullable = false)
    private String attachedBranch;

    @Column(name = "building")
    private String addressLine01;

    @Column(name = "city_lane")
    private String addressLine02;

    @Column(name = "city", nullable = false)
    private String addressLine03;

    @Column(name = "state")
    private String addressLine04;

    @Column(name = "postal_code", nullable = false)
    private String addressLine05;

    @Column(name = "contact_no", length = 15, nullable = false)
    private String contactNo;

    @Column(name = "emergency_contact", length = 10, nullable = false)
    private String emergencyContact;

    @Column(nullable = false, unique = true)
    private String email;

    private String guardian;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;

//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<SaleServiceEntity> saleServiceEntities = new ArrayList<>();

}

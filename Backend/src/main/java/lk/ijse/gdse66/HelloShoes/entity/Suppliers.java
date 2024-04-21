package lk.ijse.gdse66.HelloShoes.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Suppliers {

    @Id
    private String supplierCode;
    @Column(unique = true, nullable = false)
    private String supplierName;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String addressLine01;
    private String addressLine02;
    private String addressLine03;
    private String addressLine04;
    private String addressLine05;
    @Column(nullable = false)
    private String addressLine06;
    @Column(length = 15, nullable = false)
    private String contactNo1;
    private String contactNo2;
    @Column(unique = true, nullable = false)
    private String email;
}

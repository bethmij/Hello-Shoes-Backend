package lk.ijse.gdse66.HelloShoes.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Suppliers {

    @Id
    @Column(name = "supplier_code")
    private String supplierCode;

    @Column(name = "supplier_name",unique = true, nullable = false)
    private String supplierName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "building")
    private String addressLine01;

    @Column(name = "city_lane")
    private String addressLine02;

    @Column(name = "city")
    private String addressLine03;

    @Column(name = "state")
    private String addressLine04;

    @Column(name = "postal_code", nullable = false)
    private String addressLine05;

    @Column(name = "country", nullable = false)
    private String addressLine06;

    @Column(name = "contact_no",length = 15, nullable = false)
    private String contactNo1;

    @Column(name = "contact_no")
    private String contactNo2;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "suppliers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inventory> inventories = new ArrayList<>();
}

package lk.ijse.gdse66.HelloShoes.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.HelloShoes.service.util.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "item_desc", columnDefinition = "LONGTEXT")
    private String itemDesc;

    @Column(name = "item_picture",columnDefinition = "LONGTEXT")
    private String itemPicture;

    private String category;

    private int size;

    @ManyToOne
    @JoinColumn(name = "supplier_code", referencedColumnName = "supplier_code", unique = true)
    private Suppliers suppliers;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "saling_unit_price")
    private double saleUnitPrice;

    @Column(name = "buying_unit_price")
    private double buyUnitPrice;

    @Column(name = "expected_profit")
    private double expectedProfit;

    @Column(name = "profit_margin")
    private double profitMargin;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminPanel> adminPanels = new ArrayList<>();

    @OneToMany(mappedBy = "inventory")
    private Set<SaleInventory> saleInventories = new HashSet<>();

}

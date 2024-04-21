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
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(name = "itemCode")
    private String itemCode;

    @Column(name = "item_desc", columnDefinition = "LONGTEXT")
    private String itemDesc;

    @Column(name = "item_picture",columnDefinition = "LONGTEXT")
    private String itemPicture;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int size;

    @ManyToOne
    @JoinColumn(name = "supplier_code")
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

    private String status;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminPanel> adminPanels = new ArrayList<>();

}

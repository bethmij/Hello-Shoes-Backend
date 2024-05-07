package lk.ijse.gdse66.HelloShoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale_inventory")
public class SaleInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int qty;

    private double prize;

    @ManyToOne
    @JoinColumn(name = "order_no")
    SaleServiceEntity saleService;

    @ManyToOne
    @JoinColumn(name = "item_code")
    Inventory inventory;
}

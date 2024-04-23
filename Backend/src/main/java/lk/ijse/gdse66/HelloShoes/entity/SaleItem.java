package lk.ijse.gdse66.HelloShoes.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.HelloShoes.entity.embeddedID.SaleItemID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale_item")
public class SaleItem  {

    @EmbeddedId
    private SaleItemID saleItemID;

    @ManyToOne
    @MapsId("orderNo")
    private SaleServiceEntity saleService;

    @ManyToOne
    @MapsId("itemCode")
    private Inventory inventory;

    @Column(name = "item_qty", nullable = false)
    private int itemQty;

    @Column(name = "total_price")
    private double totalPrice;
}

package lk.ijse.gdse66.HelloShoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin_panel")
public class AdminPanel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "panel_id")
    private int panelId;

    @Column(name = "total_sales", nullable = false)
    private double totalSales;

    @Column(name = "total_profit", nullable = false)
    private double totalProfit;

    @ManyToOne
    @JoinColumn(name = "most_sale_item")
    private Inventory inventory;

    @Column(name = "most_sale_pic", columnDefinition = "LONGTEXT")
    private String mostSaleItemPic;

    @Column(name = "most_sale_item_qty")
    private int mostSaleItemQty;
}
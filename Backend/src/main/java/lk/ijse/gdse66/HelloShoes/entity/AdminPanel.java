package lk.ijse.gdse66.HelloShoes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin_panel")
public class AdminPanel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "panel_id")
    private int panelId;

    @CreationTimestamp
    @Column (unique = true, columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyyMMdd")
    private Date date;

    @Column(name = "total_sales", nullable = false)
    private double totalSales;

    @Column(name = "total_profit", nullable = false)
    private double totalProfit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "most_sale_item", referencedColumnName = "item_code")
    private Inventory inventory;


    @Column(name = "most_sale_item_qty")
    private int mostSaleItemQty;
}
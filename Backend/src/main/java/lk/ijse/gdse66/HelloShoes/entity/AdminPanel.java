package lk.ijse.gdse66.HelloShoes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

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

    @CreationTimestamp
    @Column (unique = true, columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyyMMdd")
    private Date date;

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
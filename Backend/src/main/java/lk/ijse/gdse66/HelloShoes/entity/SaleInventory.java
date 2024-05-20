package lk.ijse.gdse66.HelloShoes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Getter
@Setter
@ToString
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

    @CreationTimestamp
    @Column ( columnDefinition = "DATE", nullable = false)
    @JsonFormat(pattern = "yyyyMMdd")
    private Date purchase_data;

    @ManyToOne()
    @JoinColumn(name = "order_no")
    SaleServiceEntity saleService;

    @ManyToOne()
    @JoinColumn(name = "item_code")
    Inventory inventory;
}

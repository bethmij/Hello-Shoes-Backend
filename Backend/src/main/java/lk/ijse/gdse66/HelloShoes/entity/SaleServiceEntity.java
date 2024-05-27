package lk.ijse.gdse66.HelloShoes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Loyalty;
import lk.ijse.gdse66.HelloShoes.service.util.enums.PaymentMethod;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale_service")
public class SaleServiceEntity {

    @Id
    @Column(name = "order_no")
    private String orderID;

    @OneToMany(mappedBy = "saleService", cascade = CascadeType.ALL)
    private Set<SaleInventory> saleInventories = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_name", referencedColumnName = "customer_name")
    private Customers customers;


    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private Loyalty customerType;

//    @Column(name = "item_desc")
//    private String itemDesc;
//    private int size;

//    @Column(name = "unit_price", nullable = false)
//    private double unitPrice;

//    @Column(name = "item_qty")
//    private int itemQty;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @CreationTimestamp
    @Column ( columnDefinition = "DATE", nullable = false)
    @JsonFormat(pattern = "yyyyMMdd")
    private Date purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "added_points")
    private double addedPoints;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cashier", referencedColumnName = "employee_name", nullable = false)
    private Employee employee;

}

package lk.ijse.gdse66.HelloShoes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lk.ijse.gdse66.HelloShoes.service.util.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private String orderNo;

    @OneToMany(mappedBy = "saleService",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SaleItem> saleItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_name", referencedColumnName = "customer_name", nullable = false)
    private Customers customers;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @CreationTimestamp
    @Column (columnDefinition = "DATE", nullable = false)
    @JsonFormat(pattern = "yyyyMMdd")
    private Date purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "added_points")
    private double addedPoints;

    @ManyToOne
    @JoinColumn(name = "cashier", referencedColumnName = "employee_name", nullable = false)
    private Employee employee;

}

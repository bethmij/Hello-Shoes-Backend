package lk.ijse.gdse66.HelloShoes.dto;

import lk.ijse.gdse66.HelloShoes.service.util.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleServiceDTO {

    private String orderNo;
    private String itemCode;
    private String customerName;
    private String itemDesc;
    private int size;
    private double unitPrice;
    private int itemQty;
    private double totalPrice;
    private Timestamp purchaseDate;
    private PaymentMethod paymentMethod;
    private double addedPoints;
    private String cashier;

}

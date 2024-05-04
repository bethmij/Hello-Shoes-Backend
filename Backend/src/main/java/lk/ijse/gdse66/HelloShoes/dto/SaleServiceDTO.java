package lk.ijse.gdse66.HelloShoes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lk.ijse.gdse66.HelloShoes.service.util.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleServiceDTO {

    @NotBlank(message = "order no can not be null")
    @Pattern(regexp = "OR00-[0-9]{3}", message = "item code is not valid")
    private String orderNo;

    private Map<String, Integer> inventoryList;
 //    private String itemCode;

    @NotBlank(message = "customer name can not be null")
    @Pattern(regexp = "[A-Za-z ]+", message = "name is not valid")
    private String customerName;
//    private String itemDesc;
//    private int size;

//    @NotNull(message = "unit price can not be null")
//    @Positive(message = "unit price must be greater than zero")
//    @DecimalMin(value = "0.0", inclusive = false, message = "unit price must be greater than zero")
//    private double unitPrice;

//    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "item qty must be a valid integer")
//    private int itemQty;

    @NotNull(message = "total price can not be null")
    @Positive(message = "total price must be greater than zero")
    @DecimalMin(value = "0.0", inclusive = false, message = "total price must be greater than zero")
    private double totalPrice;

    @JsonFormat(pattern = "yyyyMMdd")
    @NotNull(message = "purchase date can not be null")
    private Date purchaseDate;

    @NotNull(message = "payment method can not be null")
    private PaymentMethod paymentMethod;
    private double addedPoints;

    @NotBlank(message = "cashier can not be null")
    private String cashier;

    private List<SaleInventoryDTO> saleInventory;

}

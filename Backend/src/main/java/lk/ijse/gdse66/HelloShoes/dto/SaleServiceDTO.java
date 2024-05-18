package lk.ijse.gdse66.HelloShoes.dto;

import jakarta.validation.constraints.*;
import lk.ijse.gdse66.HelloShoes.service.util.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleServiceDTO {

    @NotBlank(message = "order no can not be null")
    @Pattern(regexp = "OR00-[0-9]{3}", message = "item code is not valid")
    private String orderID;

    private Map<String, Integer> inventoryList;

    @NotBlank(message = "customer name can not be null")
    @Pattern(regexp = "[A-Za-z ]+", message = "name is not valid")
    private String customerName;

    @NotNull(message = "total price can not be null")
    @Positive(message = "total price must be greater than zero")
    @DecimalMin(value = "0.0", inclusive = false, message = "total price must be greater than zero")
    private double totalPrice;


    @NotNull(message = "payment method can not be null")
    private PaymentMethod paymentMethod;
    private double addedPoints;

    @NotBlank(message = "cashier can not be null")
    private String cashier;


}

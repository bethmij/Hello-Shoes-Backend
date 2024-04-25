package lk.ijse.gdse66.HelloShoes.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    @NotBlank(message = "item code can not be null")
    @Pattern(regexp = "I00-[0-9]{3}", message = "item code is not valid")
    private String itemCode;

    private String itemDesc;
    private String itemPicture;

    @NotBlank(message = "category can not be null")
    private String category;

    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "size must be a valid integer")
    private int size;

    @NotBlank(message = "supplier code can not be null")
    private String supplierCode;
    private String supplierName;

    @NotNull(message = "sale unit price sales can not be null")
    @Positive(message = "sale unit price must be greater than zero")
    @DecimalMin(value = "0.0", inclusive = false, message = "sale unit price must be greater than zero")
    private double saleUnitPrice;

    @NotNull(message = "buying unit price can not be null")
    @Positive(message = "buying unit price must be greater than zero")
    @DecimalMin(value = "0.0", inclusive = false, message = "buying unit price must be greater than zero")
    private double buyUnitPrice;
    private double expectedProfit;
    private double profitMargin;

    @NotBlank(message = "status can not be null")
    private String status;

}

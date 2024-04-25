package lk.ijse.gdse66.HelloShoes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPanelDTO {

    @JsonFormat(pattern = "yyyyMMdd")
    private Date date;

    @NotNull(message = "total sales can not be null")
    @Positive(message = "total sales must be greater than zero")
    @DecimalMin(value = "0.0", inclusive = false, message = "total sales must be greater than zero")
    private double totalSales;

    @NotNull(message = "total profit can not be null")
    @Positive(message = "total sales must be greater than zero")
    @DecimalMin(value = "0.0", inclusive = false, message = "total profit must be greater than zero")
    private double totalProfit;

    @NotBlank(message = "most sale item can not be null")
    private String mostSaleItem;
    private String mostSaleItemPic;

    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "most sale item qty must be a valid integer")
    private int mostSaleItemQty;
}
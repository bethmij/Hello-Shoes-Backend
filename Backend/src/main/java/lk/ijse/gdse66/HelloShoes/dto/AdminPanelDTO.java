package lk.ijse.gdse66.HelloShoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPanelDTO {

    @NotBlank(message = "total sales can not be null")
    @Pattern(regexp = "-?\\d+(\\.\\d+)?", message = "total sales is not valid")
    private double totalSales;

    @NotBlank(message = "total profit can not be null")
    @Pattern(regexp = "-?\\d+(\\.\\d+)?", message = "total profit is not valid")

    private double totalProfit;

    @NotBlank(message = "most sale item can not be null")
    private String mostSaleItem;
    private String mostSaleItemPic;

    @Pattern(regexp = "\\d+", message = "most sale item qty is not valid")

    private int mostSaleItemQty;
}
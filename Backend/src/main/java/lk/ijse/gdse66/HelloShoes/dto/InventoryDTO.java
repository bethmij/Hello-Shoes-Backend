package lk.ijse.gdse66.HelloShoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private String itemCode;
    private String itemDesc;
    private String itemPicture;
    private String category;
    private int size;
    private String supplierCode;
    private String supplierName;
    private double saleUnitPrice;
    private double buyUnitPrice;
    private double expectedProfit;
    private double profitMargin;
    private String status;

}

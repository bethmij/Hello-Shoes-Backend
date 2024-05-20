package lk.ijse.gdse66.HelloShoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleInventoryDTO {

    private int id;

    private String orderID;

    private String itemCode;

    private int size;

    private int itemQuantity;

    private double saleUnitPrice;

    private String itemDesc;

    private double prize;

}

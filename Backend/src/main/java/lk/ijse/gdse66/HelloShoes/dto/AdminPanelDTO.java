package lk.ijse.gdse66.HelloShoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPanelDTO {

    private double totalSales;
    private double totalProfit;
    private String mostSaleItem;
    private String mostSaleItemPic;
    private int mostSaleItemQty;
}
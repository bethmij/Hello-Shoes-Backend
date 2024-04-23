package lk.ijse.gdse66.HelloShoes.entity.embeddedID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SaleItemID implements Serializable {
    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "item_code")
    private String itemCode;

}

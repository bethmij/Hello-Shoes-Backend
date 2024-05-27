package lk.ijse.gdse66.HelloShoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefundDTO {

    @NotBlank(message = "order no can not be null")
    @Pattern(regexp = "OR00-[0-9]{3}", message = "item code is not valid")
    private String orderID;

    @NotBlank(message = "item code can not be null")
    @Pattern(regexp = "I00-[0-9]{3}", message = "item code is not valid")
    private String itemCode;

    private Date purchaseDate;

    private int itemQty;
}

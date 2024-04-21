package lk.ijse.gdse66.HelloShoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuppliersDTO {

    @NotBlank(message = "supplier code can not be null")
    @Pattern(regexp = "SP00-[0-9]{3}", message = "supplier code is not valid")
    private String supplierCode;

    @NotBlank(message = "name can not be null")
    @Pattern(regexp = "[A-Za-z ]+", message = "name is not valid")
    private String supplierName;

    @NotBlank(message = "category can not be null")
    private Category category;

    private String addressLine01;
    private String addressLine02;
    private String addressLine03;
    private String addressLine04;

    @NotBlank(message = "postal code can not be null")
    @Pattern(regexp = "\\d+", message = "postal code is not valid")
    private String addressLine05;

    @NotBlank(message = "country can not be null")
    @Pattern(regexp = "[A-Za-z ]+", message = "country is not valid")
    private String addressLine06;

    @NotBlank(message = "contact no can not be null")
    private String contactNo1;
    private String contactNo2;

    @NotBlank(message = "email can not be null")
    private String email;
}

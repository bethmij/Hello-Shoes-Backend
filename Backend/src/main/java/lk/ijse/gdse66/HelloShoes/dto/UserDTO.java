package lk.ijse.gdse66.HelloShoes.dto;

import lk.ijse.gdse66.HelloShoes.service.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private Role role;
    
}

package lk.ijse.gdse66.HelloShoes.entity;

import lk.ijse.gdse66.HelloShoes.service.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String email;
    private String password;
    private Role role;

}

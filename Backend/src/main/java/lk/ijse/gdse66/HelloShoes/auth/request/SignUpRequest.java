package lk.ijse.gdse66.HelloShoes.auth.request;

import lk.ijse.gdse66.HelloShoes.service.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
    private String email;
    private String password;
    private Role role;
}

package lk.ijse.gdse66.HelloShoes.auth.response;

import lk.ijse.gdse66.HelloShoes.service.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtAuthResponse {
    private String token;
    private Role role;
    private String profilePic;
    private String name;
}

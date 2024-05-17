package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserDetails(String email);

    UserDTO saveUser(UserDTO UserDTO);

    void updateUser(UserDTO UserDTO);

    void deleteUser(String email);

//    boolean checkPassword(UserDTO req);

//    List<UserDTO> findAllByRole(String role);

    UserDetailsService userDetailService();


//    List<String> GetSupplierCode();
}

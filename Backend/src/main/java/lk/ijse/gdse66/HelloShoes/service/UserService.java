package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserDetails(String email);

    UserDTO saveUser(UserDTO UserDTO);

    void updateUser(UserDTO UserDTO);

    void deleteUser(String email);

//    List<String> GetSupplierCode();
}

package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.UserDTO;
import lk.ijse.gdse66.HelloShoes.repository.UserRepo;
import lk.ijse.gdse66.HelloShoes.service.UserService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    Transformer transformer;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream()
                .map(user -> transformer.fromUserEntity(user)).toList();
    }

    @Override
    public UserDTO getUserDetails(String email) {
        if(userRepo.existsByEmployee_Email(email)){
            throw new NotFoundException("User email: " + email + " does not exist");
        }

        return transformer.fromUserEntity(userRepo.findByEmployee_Email(email));

    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        return transformer.fromUserEntity(
                userRepo.save(
                        transformer.toUserEntity(userDTO)));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if(userRepo.existsByEmployee_Email(userDTO.getEmail())){
            throw new NotFoundException("User email: " + userDTO.getEmail() + " does not exist");
        }

        userRepo.save(transformer.toUserEntity(userDTO));
    }

    @Override
    public void deleteUser(String email) {
        if(userRepo.existsByEmployee_Email(email)){
            throw new NotFoundException("User email: " + email + " does not exist");
        }
        userRepo.deleteByEmployee_Email(email);

    }

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}

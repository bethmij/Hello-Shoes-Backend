package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.UserDTO;
import lk.ijse.gdse66.HelloShoes.entity.Employee;
import lk.ijse.gdse66.HelloShoes.entity.User;
import lk.ijse.gdse66.HelloShoes.repository.EmployeeRepo;
import lk.ijse.gdse66.HelloShoes.repository.UserRepo;
import lk.ijse.gdse66.HelloShoes.service.UserService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    Transformer transformer;


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream()

                .map(user -> {
                            Employee employee = employeeRepo.findByEmail(user.getUsername());
                            UserDTO userDTO = transformer.fromUserEntity(user);
                            userDTO.setEmployeeID(employee.getEmployeeCode());
                            userDTO.setEmployeeName(employee.getEmployeeName());
                            userDTO.setProfilePic(employee.getProfilePic());
                            userDTO.setEmail(employee.getEmail());
                            return userDTO;

                        }
                ).toList();
    }

    @Override
    public UserDTO getUserDetails(String email) {
        if (userRepo.existsByEmployee_Email(email)) {
            throw new NotFoundException("User email: " + email + " does not exist");
        }

        return transformer.fromUserEntity(userRepo.findByEmployee_Email(email).get());

    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (!employeeRepo.existsByEmail(userDTO.getEmail())) {
            throw new NotFoundException("User email: " + userDTO.getEmail() + " does not exist");
        }

        Employee employee = employeeRepo.findByEmail(userDTO.getEmail());

        User userEntity = transformer.toUserEntity(userDTO);
        userEntity.setEmployee(employee);

        return transformer.fromUserEntity(
                userRepo.save(userEntity));

    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (userRepo.existsByEmployee_Email(userDTO.getEmail())) {
            throw new NotFoundException("User email: " + userDTO.getEmail() + " does not exist");
        }

        Employee employee = employeeRepo.findByEmail(userDTO.getEmail());

        User userEntity = transformer.toUserEntity(userDTO);
        userEntity.setEmployee(employee);

        userRepo.save(userEntity);
    }

    @Override
    public void deleteUser(String email) {
        if (userRepo.existsByEmployee_Email(email)) {
            throw new NotFoundException("User email: " + email + " does not exist");
        }
        userRepo.deleteByEmployee_Email(email);

    }

//    @Override
//    public boolean checkPassword(UserDTO req) {
//        Optional<User> details = userRepo.findByEmployee_Email(req.getEmail());
//        if (details.isPresent()) {
//            boolean matches = passwordEncoder.matches(req.getPassword(), details.get().getPassword());
//            if (matches) {
//                return true;
//            }
//        }
//        return false;
//    }

//    @Override
//    public List<UserDTO> findAllByRole(String role) {
//        if ("USER".equals(role)) {
//            return transformer.convert(userRepo.findAllByRole(Role.USER), Tranformer.ClassType.USER_DTO_LIST);
//        } else if ("ADMIN".equals(role)) {
//            return transformer.convert(userRepo.findAllByRole(Role.ADMIN), Tranformer.ClassType.USER_DTO_LIST);
//        } else {
//            throw new NotFoundException("Not : "+role+" role");
//        }
//    }

    @Override
    public UserDetailsService userDetailService() {
        return username ->
                userRepo.findByEmployee_Email(username).
                        orElseThrow(() ->
                                new UsernameNotFoundException("user not found"));
    }
}


//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }


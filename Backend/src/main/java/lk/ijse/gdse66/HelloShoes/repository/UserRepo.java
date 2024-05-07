package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {

    boolean existsByEmployee_Email(String email);

    Optional<User> findByEmployee_Email(String email);

    void deleteByEmployee_Email(String email);



//    List<User> findAllByRole(Role role);
}

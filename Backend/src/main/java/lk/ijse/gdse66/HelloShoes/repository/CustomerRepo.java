package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customers,String> {

    Customers findFirstByOrderByCustomerCodeDesc();

    Customers findByCustomerName(String name);


//    boolean existsByEmail(String email);
//
//    Customers findByEmail(String email);
}

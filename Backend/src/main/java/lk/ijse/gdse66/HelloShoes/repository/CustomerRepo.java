package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customers,String> {

    Customers findFirstByOrderByCustomerCodeDesc();

    Customers findByCustomerName(String name);

    @Query("SELECT c.customerCode FROM Customers c ORDER BY c.customerCode")
    List<String> findAllIds();


//    boolean existsByEmail(String email);
//
//    Customers findByEmail(String email);
}

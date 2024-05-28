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



    @Query(value = "SELECT * FROM customers c WHERE DATE(c.dob) = DATE(NOW()) AND (c.birthday_wish IS NULL OR c.birthday_wish < DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR))", nativeQuery = true)
    List<Customers> findCustomersWithBirthdaysToday();
//    @Query(value = "SELECT * FROM customers c WHERE DATE(c.dob) = DATE(NOW())", nativeQuery = true)
//    List<Customers> findCustomersWithBirthdaysToday();



//    boolean existsByEmail(String email);
//
//    Customers findByEmail(String email);
}

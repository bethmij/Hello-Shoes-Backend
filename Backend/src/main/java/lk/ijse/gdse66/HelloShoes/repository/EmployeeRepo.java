package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,String> {

    Employee findFirstByOrderByEmployeeCodeDesc();

    boolean existsByEmail(String email);

    Employee findByEmail(String email);

    Employee findByEmployeeName(String name);

    @Query("SELECT e.employeeCode FROM Employee e ORDER BY e.employeeCode")
    List<String> findAllIds();

}

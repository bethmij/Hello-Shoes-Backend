package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,String> {

    Employee findFirstByOrderByEmployeeCodeDesc();

    boolean existsByEmail(String email);

    Employee findByEmail(String email);

    Employee findByEmployeeName(String name);

}

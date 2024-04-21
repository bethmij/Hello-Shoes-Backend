package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Suppliers,String> {

    Suppliers findFirstByOrderBySupplierCodeDesc();
}

package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepo extends JpaRepository<Suppliers,String> {

    Suppliers findFirstByOrderBySupplierCodeDesc();

    Suppliers findBySupplierCode(String id);

    Suppliers findBySupplierName(String name);

    @Query("SELECT s.supplierCode FROM Suppliers s ORDER BY s.supplierCode")
    List<String> findAllIds();
}

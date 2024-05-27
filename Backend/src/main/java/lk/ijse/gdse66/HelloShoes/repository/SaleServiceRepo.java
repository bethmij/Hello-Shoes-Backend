package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.SaleServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleServiceRepo extends JpaRepository<SaleServiceEntity,String> {

    SaleServiceEntity findFirstByOrderByOrderIDDesc();

    @Query("SELECT s.orderID FROM SaleServiceEntity s ORDER BY s.orderID")
    List<String> findAllIds();



//    boolean existsBySuppliers_SupplierCode(String code);

}

package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.SaleServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleServiceRepo extends JpaRepository<SaleServiceEntity,String> {

    SaleServiceEntity findFirstByOrderByOrderIDDesc();


//    boolean existsBySuppliers_SupplierCode(String code);

}

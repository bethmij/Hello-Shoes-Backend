package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.SaleService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleServiceRepo extends JpaRepository<SaleService,String> {

    SaleService findFirstByOrderByOrderNoDesc();

//    boolean existsBySuppliers_SupplierCode(String code);

}

package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepo extends JpaRepository<Inventory,String> {

    Inventory findFirstByOrderByItemCodeDesc();

    boolean existsBySuppliers_SupplierCode(String code);

}

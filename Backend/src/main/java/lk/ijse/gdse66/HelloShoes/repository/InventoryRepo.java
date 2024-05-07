package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepo extends JpaRepository<Inventory,String> {

    Inventory findFirstByOrderByItemCodeDesc();

    boolean existsBySuppliers_SupplierCode(String code);

    @Query("SELECT i.itemCode FROM Inventory i ORDER BY i.itemCode")
    List<String> findAllIds();

}

package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepo extends JpaRepository<Inventory,String> {

    Inventory findFirstByOrderByItemCodeDesc();

    boolean existsBySuppliers_SupplierCode(String code);

    @Query("SELECT i.itemCode FROM Inventory i WHERE i.status != 'NOT_AVAILABLE' ORDER BY i.itemCode")
    List<String> findAllIds();

    @Query("SELECT i FROM Inventory i WHERE i.itemCode = :itemCode")
    List<Inventory> findByItemCode(@Param("itemCode") String itemCode);

    @Query("SELECT i FROM Inventory i WHERE i.status = 'LOW'")
    List<Inventory> findLowStock();



}

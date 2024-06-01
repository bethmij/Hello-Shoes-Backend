package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.ItemSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemSizeRepo extends JpaRepository<ItemSize,String> {

    List<ItemSize> findByItem_ItemCode(String itemCode);


    ItemSize findByItem_ItemCodeAndAndSize(String itemCode, String size);

//    List<ItemSize> findByItem_ItemCode(String itemCode);

//    Inventory findFirstByOrderByItemCodeDesc();
//
//    boolean existsBySuppliers_SupplierCode(String code);
//
//    @Query("SELECT i.itemCode FROM Inventory i WHERE i.status != 'NOT_AVAILABLE' ORDER BY i.itemCode")
//    List<String> findAllIds();
//
//    @Query("SELECT i FROM Inventory i WHERE i.status = 'LOW'")
//    List<Inventory> findLowStock();



}

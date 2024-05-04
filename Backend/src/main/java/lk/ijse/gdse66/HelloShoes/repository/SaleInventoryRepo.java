package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.SaleInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleInventoryRepo extends JpaRepository<SaleInventory, String> {

    List<SaleInventory> findBySaleService_OrderNo(String orderNo);
}

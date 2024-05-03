package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.SaleInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleInventoryRepo extends JpaRepository<SaleInventory, String> {
}

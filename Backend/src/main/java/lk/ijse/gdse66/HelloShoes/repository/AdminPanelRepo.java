package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.AdminPanel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPanelRepo extends JpaRepository<AdminPanel,Integer> {

    boolean existsByInventory_ItemCode(String inventory_itemCode);
}

package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.AdminPanel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface AdminPanelRepo extends JpaRepository<AdminPanel,Integer> {

    boolean existsByDate(Date date);

    AdminPanel findByDate(Date date);
}

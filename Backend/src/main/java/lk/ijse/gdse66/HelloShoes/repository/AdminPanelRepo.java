package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.AdminPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface AdminPanelRepo extends JpaRepository<AdminPanel,Integer> {

    boolean existsByDate(Date date);

    @Query("SELECT a FROM AdminPanel a WHERE DATE(a.date) = :date")
    AdminPanel findByDate(@Param("date") Date date);

}

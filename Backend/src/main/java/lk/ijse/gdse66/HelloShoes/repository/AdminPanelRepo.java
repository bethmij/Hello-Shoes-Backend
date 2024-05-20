package lk.ijse.gdse66.HelloShoes.repository;

import lk.ijse.gdse66.HelloShoes.entity.AdminPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AdminPanelRepo extends JpaRepository<AdminPanel,Integer> {

    boolean existsByDate(Date date);

    @Query("SELECT a FROM AdminPanel a WHERE DATE(a.date) = :date")
    AdminPanel findByDate(@Param("date") Date date);

    @Query("SELECT a FROM AdminPanel a WHERE a.date BETWEEN :startDate AND :endDate ORDER BY a.totalProfit DESC LIMIT 1")
    AdminPanel findSalesForWeek(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(a.totalProfit) FROM AdminPanel a WHERE a.date BETWEEN :startDate AND :endDate")
    Double findTotalProfitForWeek(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(a.totalSales) FROM AdminPanel a WHERE a.date BETWEEN :startDate AND :endDate")
    Double findTotalSalesForWeek(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT a FROM AdminPanel a WHERE a.date BETWEEN :startDate AND :endDate ")
    List<AdminPanel> findWeekData(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}


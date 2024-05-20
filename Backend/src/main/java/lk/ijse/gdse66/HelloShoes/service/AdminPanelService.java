package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.AdminPanelDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface AdminPanelService {
    List<AdminPanelDTO> getAllAdminPanel();

    List<AdminPanelDTO> getAllAdminPanelByDate(LocalDate startDate, LocalDate endDate);

    AdminPanelDTO getAdminPanelByDate(Date date);

    AdminPanelDTO saveAdminPanel(AdminPanelDTO AdminPanelDTO);

    AdminPanelDTO getProfitForWeek(LocalDate startDate, LocalDate endDate);

    Double getTotalProfitForWeek(LocalDate startDate, LocalDate endDate);

    Double findTotalSalesForWeek(LocalDate startDate, LocalDate endDate);

//    void updateAdminPanel(AdminPanelDTO AdminPanelDTO);
//
//    void deleteAdminPanel(String id);

//    List<String> GetSupplierCode();
}

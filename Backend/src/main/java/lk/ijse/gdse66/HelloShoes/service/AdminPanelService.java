package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.AdminPanelDTO;

import java.sql.Date;
import java.util.List;

public interface AdminPanelService {
    List<AdminPanelDTO> getAllAdminPanel();

    AdminPanelDTO getAdminPanelByDate(Date date);

    AdminPanelDTO saveAdminPanel(AdminPanelDTO AdminPanelDTO);

//    void updateAdminPanel(AdminPanelDTO AdminPanelDTO);
//
//    void deleteAdminPanel(String id);

//    List<String> GetSupplierCode();
}

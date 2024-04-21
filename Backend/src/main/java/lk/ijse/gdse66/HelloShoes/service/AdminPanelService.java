package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.AdminPanelDTO;

import java.util.List;

public interface AdminPanelService {
    List<AdminPanelDTO> getAllAdminPanel();

    AdminPanelDTO getAdminPanel(String id);

    AdminPanelDTO saveAdminPanel(AdminPanelDTO AdminPanelDTO);

    void updateAdminPanel(AdminPanelDTO AdminPanelDTO);

    void deleteAdminPanel(String id);

//    List<String> GetSupplierCode();
}

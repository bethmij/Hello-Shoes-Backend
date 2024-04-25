package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.AdminPanelDTO;
import lk.ijse.gdse66.HelloShoes.repository.AdminPanelRepo;
import lk.ijse.gdse66.HelloShoes.service.AdminPanelService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class AdminPanelServiceImpl implements AdminPanelService {

    @Autowired
    AdminPanelRepo adminPanelRepo;

    @Autowired
    Transformer transformer;

    @Override
    public List<AdminPanelDTO> getAllAdminPanel() {
        return adminPanelRepo.findAll().stream()
                .map(adminPanel -> transformer.fromAdminPanelEntity(adminPanel))
                .toList();
    }

    @Override
    public AdminPanelDTO getAdminPanelByDate(Date date) {
        if(!adminPanelRepo.existsByDate(date)){
            throw new NotFoundException("Panel date: " + date + " does not exist");
        }

        return transformer.fromAdminPanelEntity(adminPanelRepo.findByDate(date));

    }

    @Override
    public AdminPanelDTO saveAdminPanel(AdminPanelDTO adminPanelDTO) {

        return transformer.fromAdminPanelEntity(
                adminPanelRepo.save(
                        transformer.toAdminPanelEntity(adminPanelDTO)));
    }
//
//    @Override
//    public void updateAdminPanel(AdminPanelDTO adminPanelDTO) {
//        if(!adminPanelRepo.existsByInventory_ItemCode(adminPanelDTO.getMostSaleItem())){
//            throw new NotFoundException("Supplier Code: " + suppliersDTO.getSupplierCode() + " does not exist");
//        }
//
//        supplierRepo.save(transformer.toSupplierEntity(suppliersDTO));
//    }
//
//    @Override
//    public void deleteSupplier(String id) {
//        if(!supplierRepo.existsById(id)){
//            throw new NotFoundException("Supplier Code: " + id + " does not exist");
//        }
//        supplierRepo.deleteById(id);
//
//    }

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}

package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.AdminPanelDTO;
import lk.ijse.gdse66.HelloShoes.entity.AdminPanel;
import lk.ijse.gdse66.HelloShoes.entity.Inventory;
import lk.ijse.gdse66.HelloShoes.repository.AdminPanelRepo;
import lk.ijse.gdse66.HelloShoes.repository.InventoryRepo;
import lk.ijse.gdse66.HelloShoes.service.AdminPanelService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AdminPanelServiceImpl implements AdminPanelService {

    @Autowired
    AdminPanelRepo adminPanelRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    InventoryRepo inventoryRepo;

    @Override
    public List<AdminPanelDTO> getAllAdminPanel() {

        return adminPanelRepo.findAll().stream()
                .map(adminPanel -> {
                    Inventory item = inventoryRepo.findById(adminPanel.getInventory().getItemCode()).get();

                    AdminPanelDTO adminPanelDTO = transformer.fromAdminPanelEntity(adminPanel);
                    adminPanelDTO.setMostSaleItem(item.getItemCode());
                    adminPanelDTO.setMostSaleItemPic(item.getItemPicture());

                    return adminPanelDTO;
                })
                .toList();
    }

    @Override
    public List<AdminPanelDTO> getAllAdminPanelByDate(LocalDate startDate, LocalDate endDate) {
        return adminPanelRepo.findWeekData(startDate,endDate).stream()
                .map(adminPanel -> {
                    Inventory item = inventoryRepo.findById(adminPanel.getInventory().getItemCode()).get();

                    AdminPanelDTO adminPanelDTO = transformer.fromAdminPanelEntity(adminPanel);
                    adminPanelDTO.setMostSaleItem(item.getItemCode());
                    adminPanelDTO.setMostSaleItemPic(item.getItemPicture());

                    return adminPanelDTO;
                })
                .toList();
    }

    @Override
    public AdminPanelDTO getAdminPanelByDate(Date date) {
        if(!adminPanelRepo.existsByDate(date)){
            throw new NotFoundException("Panel date: " + date + " does not exist");
        }
        AdminPanel adminPanel = adminPanelRepo.findByDate(date);
        Inventory item = inventoryRepo.findById(adminPanel.getInventory().getItemCode()).get();

        AdminPanelDTO adminPanelDTO = transformer.fromAdminPanelEntity(adminPanel);
        adminPanelDTO.setMostSaleItem(item.getItemCode());
        adminPanelDTO.setMostSaleItemPic(item.getItemPicture());

        return adminPanelDTO;

    }

    @Override
    public AdminPanelDTO saveAdminPanel(AdminPanelDTO adminPanelDTO) {

        return transformer.fromAdminPanelEntity(
                adminPanelRepo.save(
                        transformer.toAdminPanelEntity(adminPanelDTO)));
    }

    public AdminPanelDTO getProfitForWeek(LocalDate startDate, LocalDate endDate) {
        AdminPanel adminPanel = adminPanelRepo.findSalesForWeek(startDate, endDate);
        Inventory item = inventoryRepo.findById(adminPanel.getInventory().getItemCode()).get();

        AdminPanelDTO adminPanelDTO = transformer.fromAdminPanelEntity(adminPanel);
        adminPanelDTO.setMostSaleItem(item.getItemCode());
        adminPanelDTO.setMostSaleItemPic(item.getItemPicture());

        return adminPanelDTO;

    }

    public Double getTotalProfitForWeek(LocalDate startDate, LocalDate endDate) {
        return adminPanelRepo.findTotalProfitForWeek(startDate, endDate);
    }

    @Override
    public Double findTotalSalesForWeek(LocalDate startDate, LocalDate endDate) {
        return adminPanelRepo.findTotalSalesForWeek(startDate, endDate);
    }

    Double findTotalProfitForWeek(LocalDate startDate, LocalDate endDate) {
        return adminPanelRepo.findTotalProfitForWeek(startDate, endDate);
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

package lk.ijse.gdse66.HelloShoes.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.HelloShoes.dto.AdminPanelDTO;
import lk.ijse.gdse66.HelloShoes.service.AdminPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
//
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminPanelController {

    @Autowired
    AdminPanelService adminPanelService;

    @GetMapping(value ="/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminPanelDTO> getAllAdminPanel(){
        return adminPanelService.getAllAdminPanel();
    }

    @GetMapping(path = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AdminPanelDTO getAdminPanelByDate(@PathVariable("date") LocalDate date){
        return adminPanelService.getAdminPanelByDate(Date.valueOf(date));
    }

    @GetMapping("/week")
    public AdminPanelDTO getSalesForWeek() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(6);
        return adminPanelService.getProfitForWeek(startDate, endDate);
    }

    @GetMapping("/week-data")
    public List<AdminPanelDTO> getWeekData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(6);
        return adminPanelService.getAllAdminPanelByDate(startDate, endDate);
    }

    @GetMapping("/month-data")
    public List<AdminPanelDTO> getMonthData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);
        return adminPanelService.getAllAdminPanelByDate(startDate, endDate);
    }

    @GetMapping("/week-profit")
    public Double getTotalProfitForWeek() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(6);
        return adminPanelService.getTotalProfitForWeek(startDate, endDate);
    }

    @GetMapping("/week-sale")
    public Double getTotalSaleForWeek() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(6);
        return adminPanelService.findTotalSalesForWeek(startDate, endDate);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AdminPanelDTO saveAdminPanel( @Valid @RequestBody AdminPanelDTO adminPanelDTO){
        return adminPanelService.saveAdminPanel(adminPanelDTO);
    }

//    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateSupplier(@Valid @RequestBody SuppliersDTO suppliersDTO){
//        AdminPanel.updateSupplier(suppliersDTO);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteSupplier(@PathVariable("id") String id){
//        AdminPanel.deleteSupplier(id);
//    }


}

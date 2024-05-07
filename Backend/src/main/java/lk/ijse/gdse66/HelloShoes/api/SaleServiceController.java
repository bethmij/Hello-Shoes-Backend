package lk.ijse.gdse66.HelloShoes.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.HelloShoes.dto.SaleInventoryDTO;
import lk.ijse.gdse66.HelloShoes.dto.SaleServiceDTO;
import lk.ijse.gdse66.HelloShoes.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
@CrossOrigin
public class SaleServiceController {

    @Autowired
    SaleService saleService;

    @GetMapping(value ="/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SaleServiceDTO> getAllSaleService(){
        return saleService.getAllSaleService();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SaleServiceDTO getSaleServiceById(@PathVariable("id") String id){
        return saleService.getSaleServiceDetails(id);
    }

    @GetMapping(path = "/nextID")
    public String getOrderID(){
        return saleService.getOrderID();
    }

    @GetMapping(path = "/getItem/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SaleInventoryDTO> getAllSaleService(@PathVariable("id") String id){
        return saleService.getSaleInventory(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SaleServiceDTO saveSaleService( @Valid @RequestBody SaleServiceDTO saleServiceDTO){
        return saleService.saveSaleService(saleServiceDTO);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSaleService(@Valid @RequestBody SaleServiceDTO SaleServiceDTO){
        saleService.updateSaleService(SaleServiceDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSaleService(@PathVariable("id") String id){
        saleService.deleteSaleService(id);
    }


}

package lk.ijse.gdse66.HelloShoes.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.HelloShoes.dto.SuppliersDTO;
import lk.ijse.gdse66.HelloShoes.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping(value = {"/getAll"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SuppliersDTO> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuppliersDTO saveSupplier( @Valid @RequestBody SuppliersDTO suppliersDTO){
        return supplierService.saveSupplier(suppliersDTO);
    }

}

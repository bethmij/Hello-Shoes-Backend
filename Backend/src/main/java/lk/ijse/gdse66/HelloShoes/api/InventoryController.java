package lk.ijse.gdse66.HelloShoes.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.HelloShoes.dto.InventoryDTO;
import lk.ijse.gdse66.HelloShoes.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@CrossOrigin
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping(value ="/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InventoryDTO> getAllInventory(){
        return inventoryService.getAllInventory();
    }

    @GetMapping(value ="/getIDs")
    public List<String> getAllInventoryIDs(){
        return inventoryService.getAllItemCodes();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InventoryDTO getInventoryById(@PathVariable("id") String id){
        return inventoryService.getInventoryDetails(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryDTO saveInventory( @Valid @RequestBody InventoryDTO inventoryDTO){
        return inventoryService.saveInventory(inventoryDTO);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInventory(@Valid @RequestBody InventoryDTO inventoryDTO){
        inventoryService.updateInventory(inventoryDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventory(@PathVariable("id") String id){
        inventoryService.deleteInventory(id);
    }


}

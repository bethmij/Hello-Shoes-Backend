package lk.ijse.gdse66.HelloShoes.service.util;

import lk.ijse.gdse66.HelloShoes.dto.*;
import lk.ijse.gdse66.HelloShoes.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {



    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public SuppliersDTO fromSupplierEntity(Suppliers supplier){
        return mapper.map(supplier, SuppliersDTO.class);
    }

    public Suppliers toSupplierEntity(SuppliersDTO suppliersDTO){
        return mapper.map(suppliersDTO, Suppliers.class);
    }

    public AdminPanelDTO fromAdminPanelEntity(AdminPanel adminPanel){
        return mapper.map(adminPanel, AdminPanelDTO.class);
    }

    public AdminPanel toAdminPanelEntity(AdminPanelDTO adminPanelDTO){
        return mapper.map(adminPanelDTO, AdminPanel.class);
    }

    public UserDTO fromUserEntity(User user){return mapper.map(user, UserDTO.class);}

    public User toUserEntity(UserDTO userDTO){
        return mapper.map(userDTO, User.class);
    }

    public EmployeeDTO fromEmployeeEntity(Employee employee){return mapper.map(employee, EmployeeDTO.class);}

    public Employee toEmployeeEntity(EmployeeDTO employeeDTO){
        return mapper.map(employeeDTO, Employee.class);
    }

    public CustomersDTO fromCustomerEntity(Customers customers){return mapper.map(customers, CustomersDTO.class);}

    public Customers toCustomerEntity(CustomersDTO customersDTO){
        return mapper.map(customersDTO, Customers.class);
    }

    public InventoryDTO fromInventoryEntity(Inventory inventory){return mapper.map(inventory, InventoryDTO.class);}

    public Inventory toInventoryEntity(InventoryDTO inventoryDTO){
        return mapper.map(inventoryDTO, Inventory.class);
    }

    public SaleServiceDTO fromSaleServiceEntity(SaleServiceEntity saleServiceEntity){return mapper.map(saleServiceEntity, SaleServiceDTO.class);}

    public SaleServiceEntity toSaleServiceEntity(SaleServiceDTO saleServiceDTO){
        return mapper.map(saleServiceDTO, SaleServiceEntity.class);
    }

}

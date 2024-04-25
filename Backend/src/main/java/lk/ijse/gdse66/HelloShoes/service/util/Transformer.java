package lk.ijse.gdse66.HelloShoes.service.util;

import lk.ijse.gdse66.HelloShoes.dto.AdminPanelDTO;
import lk.ijse.gdse66.HelloShoes.dto.SuppliersDTO;
import lk.ijse.gdse66.HelloShoes.dto.UserDTO;
import lk.ijse.gdse66.HelloShoes.entity.AdminPanel;
import lk.ijse.gdse66.HelloShoes.entity.Suppliers;
import lk.ijse.gdse66.HelloShoes.entity.User;
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

}

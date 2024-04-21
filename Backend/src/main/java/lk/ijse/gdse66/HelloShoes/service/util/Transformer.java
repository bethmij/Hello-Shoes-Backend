package lk.ijse.gdse66.HelloShoes.service.util;

import lk.ijse.gdse66.HelloShoes.dto.SuppliersDTO;
import org.apache.logging.log4j.util.Supplier;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {

    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public SuppliersDTO fromSupplierEntity(Supplier supplier){
        return mapper.map(supplier, SuppliersDTO.class);
    }

    public Supplier toSupplierEntity(SuppliersDTO suppliersDTO){
        return mapper.map(suppliersDTO, Supplier.class);
    }

//    public ItemDTO fromItemEntity(Item item){
//        return mapper.map(item, ItemDTO.class);
//    }
//
//    public Item toItemEntity(ItemDTO itemDTO){
//        return mapper.map(itemDTO, Item.class);
//    }
//
//    public OrderDTO fromOrderEntity(Order order){
//        return mapper.map(order, OrderDTO.class);
//    }
//
//    public Order toOrderEntity(OrderDTO orderDTO){
//        return mapper.map(orderDTO, Order.class);
//    }
//
//    public OrderDetailsDTO fromOrderDetailEntity(OrderDetails orderDetails){
//        return mapper.map(orderDetails, OrderDetailsDTO.class);
//    }
//
//    public OrderDetails toOrderDetailEntity(OrderDetailsDTO orderDetailsDTO){
//        return mapper.map(orderDetailsDTO, OrderDetails.class);
//    }
}

package lk.ijse.gdse66.HelloShoes.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_size")
public class ItemSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "item_code", nullable = false)
    private Inventory item;

    public ItemSize(String size, Integer quantity) {
        this.size = size;
        this.quantity = quantity;
    }
}

package lk.ijse.gdse66.HelloShoes.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {


    @Id
    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Employee employee;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}

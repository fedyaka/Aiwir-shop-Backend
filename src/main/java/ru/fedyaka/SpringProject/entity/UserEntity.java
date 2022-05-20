package ru.fedyaka.SpringProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String Name;

    @Column(name = "customer_surname")
    private String Surname;

    @Column(name = "number_phone", nullable = false, length = 25)
    private String number_phone;

    @Column(name = "mail")
    private String mail;

//    @OneToMany(mappedBy = "user")
//    private Set<OrderEntity> orders;

}

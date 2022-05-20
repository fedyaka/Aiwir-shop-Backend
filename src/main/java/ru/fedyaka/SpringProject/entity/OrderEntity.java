package ru.fedyaka.SpringProject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, insertable = false, updatable = true)
    private long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;
    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String phoneNumber;

    @NonNull
    @Column(name = "address")
    private String address;

    @Column(name = "description", length = 2048)
    private String description;

//    @ManyToMany
//    @JoinTable(name = "order_item",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @NonNull
    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;

    @NonNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end")
    private LocalDateTime endDate;


}

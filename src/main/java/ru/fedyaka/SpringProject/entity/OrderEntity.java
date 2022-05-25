package ru.fedyaka.SpringProject.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


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

    @NonNull
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderItemEntity> orderItems;

    @NonNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end")
    private LocalDateTime endDate;


}

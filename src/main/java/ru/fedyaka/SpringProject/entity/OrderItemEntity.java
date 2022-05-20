package ru.fedyaka.SpringProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @ManyToOne
    private OrderEntity order;
    @ManyToOne
    private ProductEntity product;

    private Integer amount;


}

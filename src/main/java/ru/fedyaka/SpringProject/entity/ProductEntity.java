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
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)//name = "название столбца", nullable = "может ли быть пустым?", unique = "уникальный?"
    private Long id;

    @Column(name = "name", nullable = false, length = 255)//length = "длинна строки"
    private String name;

    @Column(name = "description", nullable = false, length = 1024)
    private String description;

    //TODO нужно переделать в long
    @Column(name = "cost", nullable = false, precision = 2)//precision = "знаков после запятой"
    private Double cost;

    @Column(name = "image")
    private String image;

    //@ManyToOne создаёт связь многие к одному между таблицами,
    //в данном случае получаем один объект category, соединив таблицы
    //при помощи @JoinColumn по полю category_id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")
    private Set<OrderItemEntity> orderItems;

}


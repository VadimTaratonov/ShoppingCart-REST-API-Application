package ru.taratonov.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "in_stock")
    private boolean inStock;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetailList;
}

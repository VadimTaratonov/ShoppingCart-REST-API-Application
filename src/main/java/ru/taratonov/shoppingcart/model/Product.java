package ru.taratonov.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonManagedReference(value = "product_order_detail")
    private List<OrderDetail> orderDetailList;

}

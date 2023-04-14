package ru.taratonov.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderDetail")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
}

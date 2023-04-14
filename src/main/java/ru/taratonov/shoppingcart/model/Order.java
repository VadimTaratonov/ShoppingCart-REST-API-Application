package ru.taratonov.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;
import ru.taratonov.shoppingcart.enumerations.OrderStatus;
import ru.taratonov.shoppingcart.enumerations.PaymentMethod;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"Order\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_date")
    @NonNull
    private Date orderDate;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    @NonNull
    private OrderStatus orderStatus;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    @NonNull
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetailList;
}

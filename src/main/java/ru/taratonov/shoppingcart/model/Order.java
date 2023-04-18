package ru.taratonov.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.taratonov.shoppingcart.enumerations.OrderStatus;
import ru.taratonov.shoppingcart.enumerations.PaymentMethod;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
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
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
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
    @JsonBackReference(value = "customer_order")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "order_order_detail")
    private List<OrderDetail> orderDetailList;
}

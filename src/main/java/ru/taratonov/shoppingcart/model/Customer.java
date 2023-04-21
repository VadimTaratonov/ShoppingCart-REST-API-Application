package ru.taratonov.shoppingcart.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "surname")
    @NonNull
    private String surname;

    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "phone")
    @NonNull
    private Long phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonManagedReference(value = "customer_order")
    private List<Order> orderList;


}

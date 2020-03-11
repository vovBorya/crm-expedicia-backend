package ua.od.onpu.crm.dao.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer_phone_numbers")
public class CustomerPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_number")
    private Integer id;

    @Column(name = "phone_number", length = 14)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_customer")
    private Customer customer;
}

package ua.od.onpu.crm.dao.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "customer_phone_numbers")
public class CustomerPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "phone_number", length = 14)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customers_id")
    private Customer customer;
}

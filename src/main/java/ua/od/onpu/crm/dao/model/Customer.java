package ua.od.onpu.crm.dao.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Integer id;

    @Column(name = "last_name", length = 40)
    private String lastName;

    @Column(name = "first_name", length = 40)
    private String firstName;

    @Column(name = "patronymic", length = 40)
    private String patronymic;

    @Column(name = "email_address", length = 50)
    private String email;

    @OneToMany(
            mappedBy = "parent",
            cascade = {CascadeType.REMOVE, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Child> children;

    @OneToMany(
            mappedBy = "customer",
            cascade = {CascadeType.REMOVE, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Deal> deals;

    @OneToMany(
            mappedBy = "customer",
            cascade = {CascadeType.REMOVE, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<CustomerPhone> phones;
}

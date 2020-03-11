package ua.od.onpu.crm.dao.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deal")
    private Integer id;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "child_id")
    private Child child;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "expedition_id")
    private Expedition expedition;
}

package ua.od.onpu.crm.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.od.onpu.crm.dao.model.enums.DealStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deals")
@Builder
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DealStatus status;

    @Column(name = "discount")
    private String discount;

    @Column(name = "departure_place")
    private String departurePlace;

    @Column(name = "transportation_way")
    private String transportationWay;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "employee_id")
    private Employee employee;

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

    @OneToMany(
            mappedBy = "deal",
            cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Payment> payments;

    @Column(name = "sleeping_bag")
    private Boolean sleepingBag;
}

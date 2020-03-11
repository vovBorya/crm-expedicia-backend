package ua.od.onpu.crm.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.od.onpu.crm.config.GlobalConfig;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_children")
    private Integer id;

    @Column(name = "last_name", length = 40)
    private String lastName;

    @Column(name = "first_name", length = 40)
    private String firstName;

    @Column(name = "patronymic", length = 40)
    private String patronymic;

    @Column(name = "birth_day")
    @JsonFormat(pattern = GlobalConfig.DATE_FORMAT_PATTERN)
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_customer")
    private Customer parent;

    @OneToMany(
            mappedBy = "child",
            cascade = {CascadeType.REMOVE, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Deal> deals;
}

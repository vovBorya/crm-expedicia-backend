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
@Table(name = "expeditions")
public class Expedition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expedition")
    private Integer id;

    @Column(name = "name_location", length = 40)
    private String location;

    @Column(name = "abbreviation", length = 5)
    private String abbreviation;

    @OneToMany(
            mappedBy = "expedition",
            cascade = {CascadeType.REMOVE, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Deal> deals;
}

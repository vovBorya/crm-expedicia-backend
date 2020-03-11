package ua.od.onpu.crm.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.od.onpu.crm.config.GlobalConfig;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Integer id;

    @Column(name = "paid_at")
    @JsonFormat(pattern = GlobalConfig.DATE_FORMAT_PATTERN_WITH_TIME)
    private LocalDateTime paid_at;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "deal_id")
    private Deal deal;

}

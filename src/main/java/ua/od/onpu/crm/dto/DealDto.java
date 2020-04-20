package ua.od.onpu.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.od.onpu.crm.dao.model.enums.DealStatus;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealDto {

    private Integer id;

    private Integer sum;

    private DealStatus status;

    private String discount;

    private String departurePlace;

    private String transportationWay;

    private String comment;

    private Integer employeeId;

    private Integer customerId;

    private Integer childId;

    private Integer expeditionId;

    private Boolean sleepingBag;
}

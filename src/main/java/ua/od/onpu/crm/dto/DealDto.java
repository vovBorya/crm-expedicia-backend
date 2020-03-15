package ua.od.onpu.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.od.onpu.crm.dao.model.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealDto {

    private Integer id;

    private Integer sum;

    private Status status;

    private String comment;

    private Integer managerId;

    private Integer customerId;

    private Integer childId;

    private Integer expeditionId;

    private Boolean sleepingBag;
}

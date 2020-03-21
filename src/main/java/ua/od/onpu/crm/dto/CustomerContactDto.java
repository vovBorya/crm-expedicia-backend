package ua.od.onpu.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.od.onpu.crm.dao.model.enums.ContactType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerContactDto {

    private Integer id;

    private ContactType type;

    private String content;

    private Integer customerId;
}

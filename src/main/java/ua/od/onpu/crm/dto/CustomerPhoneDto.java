package ua.od.onpu.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerPhoneDto {

    private Integer id;

    private String phone;

    private Integer customerId;
}

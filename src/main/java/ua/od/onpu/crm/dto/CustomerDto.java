package ua.od.onpu.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.od.onpu.crm.dao.model.enums.CustomerS;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Integer id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private String fullName;

    private String city;

    private CustomerS status;

    private String exemptions;
}

package ua.od.onpu.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

package ua.od.onpu.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

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

    private String status;

    private String exemptions;
}

package ua.od.onpu.crm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.od.onpu.crm.config.GlobalConfig;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChildDto {

    private Integer id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private String fullName;

    private String points;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GlobalConfig.DATE_FORMAT_PATTERN)
    private LocalDate birthday;

    private String city;

    private String status;

    private Integer parentId;
}

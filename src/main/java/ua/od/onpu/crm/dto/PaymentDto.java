package ua.od.onpu.crm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.od.onpu.crm.config.GlobalConfig;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    private Integer id;

    @JsonFormat(pattern = GlobalConfig.DATE_FORMAT_PATTERN_WITH_TIME)
    private LocalDateTime paidAt;

    private Integer dealId;

    private Integer sum;
}

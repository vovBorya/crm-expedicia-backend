package ua.od.onpu.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class GlobalConfig {
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String DATE_FORMAT_PATTERN_WITH_TIME = "yyyy-MM-dd'T'HH:mm";
}

package ua.od.onpu.crm.service.provider;

import org.springframework.stereotype.Component;

@Component
public class NameProvider {
    public String getFullName(String lastName, String firstName, String patronymic) {
        return String.format("%s %s %s", lastName, firstName, patronymic != null ? patronymic : "");
    }
}

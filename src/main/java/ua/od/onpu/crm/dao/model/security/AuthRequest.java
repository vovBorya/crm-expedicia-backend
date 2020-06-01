package ua.od.onpu.crm.dao.model.security;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}

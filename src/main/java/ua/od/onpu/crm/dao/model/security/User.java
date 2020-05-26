package ua.od.onpu.crm.dao.model.security;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String userName;
    private String password;
    private String roles;
}

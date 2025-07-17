package com.land.LandManagement.domain.tables;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column (name = "age")
    private Integer age;

    @Column (name = "address")
    private String address;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    public String getUsername() {
        return this.name;
    }

}

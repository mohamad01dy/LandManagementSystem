package com.land.LandManagement.domain.tables;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column (name = "age")
    private Integer age;

    @NotBlank
    @Column (name = "address")
    private String address;

    @NotBlank
    @Column (name = "email")
    private String email;

    @NotBlank
    @Column (name = "password")
    private String password;

    // Lands currently owned
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Land> lands;

    // Ownership history (past and current)
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<OwnershipHistory> ownershipHistory;

    // Buy requests sent by this user
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<BuyRequest> sentBuyRequests;

    // Buy requests received by this user
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<BuyRequest> receivedBuyRequests;

    public String getUsername() {
        return this.name;
    }

}

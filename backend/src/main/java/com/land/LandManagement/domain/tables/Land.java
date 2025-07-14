package com.land.LandManagement.domain.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "location")
    private String location;

    @Column (name = "surface")
    private float surface;

    @Column (name = "usage_Description")
    private String usageDescr;

    @Column (name = "price")
    private int price;

    @Column (name = "contact")
    private String contact;

    @Column (name = "owner_history")
    private String[] ownerHistory;
}

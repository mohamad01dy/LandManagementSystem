package com.land.LandManagement.domain.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "name")
    private String name;

    @Column (name = "location")
    private String location;

    @Column (name = "surface")
    private Float surface;

    @Column (name = "usage_Description")
    private String usageDescr;

    @Column (name = "price")
    private Integer price;

    @Column (name = "contact")
    private String contact;

    @Column (name = "latitude")
    private Float latitude;

    @Column (name = "longitude")
    private Float longitude;

    // Many ownership history records linked to this land
    @OneToMany(mappedBy = "land", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OwnershipHistory> ownershipHistory;

    // Current owner
    @ManyToOne
    @JoinColumn(name = "owner_id") // foreign key in the land table
    private User owner;

}

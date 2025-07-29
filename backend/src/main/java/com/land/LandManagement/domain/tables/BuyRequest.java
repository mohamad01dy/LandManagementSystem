package com.land.LandManagement.domain.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(name = "status")
    private String status;

    // Buyer
    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    // Seller
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = true)
    private User seller;

    // Land involved in the request
    @ManyToOne
    @JoinColumn(name = "land_id", nullable = false)
    private Land land;

}

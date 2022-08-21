package com.colendi.onlinePaymentProcess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable=false,nullable=false)
    private Long id;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "cvv", length = 3)
    private String cvv;

    @Column(name = "is_canceled")
    private Boolean isCancelled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "card_merchant_map",
    joinColumns = {@JoinColumn(name = "card_id", foreignKey = @ForeignKey(name = "FKCARD"))},
    inverseJoinColumns = {@JoinColumn(name = "merchant_id", foreignKey = @ForeignKey(name = "FKMERCHANT"))})
    private Set<Merchant> merchants;
}

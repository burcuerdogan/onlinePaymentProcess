package com.colendi.onlinePaymentProcess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable=false,nullable=false)
    private Long id;

    @Column(name = "mcc")
    private String mcc;

    @OneToOne
    @JoinColumn(name = "gpa_id", foreignKey = @ForeignKey(name = "FKMERCHANTGPA"))
    private GPA gpa;
}

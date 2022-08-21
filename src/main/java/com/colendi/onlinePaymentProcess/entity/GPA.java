package com.colendi.onlinePaymentProcess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "gpa")
public class GPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable=false,nullable=false)
    private Long id;

    @Column(name = "balance")
    private Double balance;
}

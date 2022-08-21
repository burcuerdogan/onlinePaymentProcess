package com.colendi.onlinePaymentProcess.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "card_transaction")
public class CardTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable=false,nullable=false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "created_date")
    @CreatedDate
    private Timestamp createdDate;

}

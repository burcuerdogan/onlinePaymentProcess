package com.colendi.onlinePaymentProcess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable=false,nullable=false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gpa_id", foreignKey = @ForeignKey(name = "FKUSERGPA"))
    private GPA gpa;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_card_map",
            joinColumns = {@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FKUSERID"))},
            inverseJoinColumns = {@JoinColumn(name = "card_id", foreignKey = @ForeignKey(name = "FKCARDID"))})
    private Set<Card> cards;

}

package com.GoShare.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id", updatable = false)
    private Long car_id;

    @Column(name = "car_number", nullable = false, unique = true)
    private String car_number;

    @Column(name = "car_name", nullable = false)
    private String car_name;

    @Column(name="car_type", nullable = false)
    private String car_type;

    @Column(name = "car_model", nullable = false)
    private String car_model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}

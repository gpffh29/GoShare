package com.GoShare.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Car {

    @Id
    @Column(name = "car_id", updatable = false, unique = true)
    private String car_id;

    @Column(name = "car_name", nullable = false)
    private String car_name;

    @Column(name="car_type", nullable = false)
    private String car_type;

    @Column(name = "car_model", nullable = false)
    private String car_model;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}

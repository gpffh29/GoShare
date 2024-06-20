package com.GoShare.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id", updatable = false)
    private Long id;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "startDate", nullable = false)
    private String startDate;

    @Column(name = "lastDate", nullable = false)
    private String lastDate;

    @Column(name = "loaner", nullable = false)
    private String loaner;

    @Column(name = "car_name", nullable = false)
    private String carName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}

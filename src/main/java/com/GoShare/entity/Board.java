package com.GoShare.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

//글 작성 Entity
@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "carImg", nullable = false)
    private String carImg;

    @Column(name = "content")
    private String content;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "startDate", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date startDate;

    @Column(name = "lastDate", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date lastDate;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Builder
    public Board(String carImg, String content, String region, Date startDate, Date lastDate, Integer price) {
        this.carImg = carImg;
        this.content = content;
        this.region = region;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.price = price;
    }

//    글 수정
    public void update(String carImg, String content, String region, Date startDate, Date lastDate, Integer price) {
        this.carImg = carImg;
        this.content = content;
        this.region = region;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.price = price;
    }

}

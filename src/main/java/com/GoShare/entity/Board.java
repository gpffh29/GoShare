package com.GoShare.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//글 작성 Entity
@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", updatable = false)
    private Long id;


    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;


    @Column(name = "lastDate", nullable = false)
    private LocalDate lastDate;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "car_name")
    private String carName;

//    글 생성, 수정 시간 추가
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardImage> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Board(String content, String region, LocalDate startDate, LocalDate lastDate, Integer price, List<BoardImage> images, Member member, String carModel, String carName, String carType) {

        this.content = content;
        this.region = region;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.price = price;
        if (images != null) {
            this.images = images;
            for (BoardImage image : images) {
                image.setBoard(this);
            }
        }
        this.member = member;
        this.carType = carType;
        this.carModel = carModel;
        this.carName = carName;
    }

//    글 수정
    public void update(String content, String region, LocalDate startDate, LocalDate lastDate, Integer price) {

        this.content = content;
        this.region = region;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.price = price;

    }

//    이미지 수정
    public void updateImages(List<BoardImage> newImages){
        this.images.clear();
        if(newImages != null){
            this.images.addAll(newImages);
            for(BoardImage image : newImages) {
                image.setBoard(this);
            }
        }
    }

}

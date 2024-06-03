package com.GoShare.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//글 작성 Entity
@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date startDate;

    @Column(name = "lastDate", nullable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date lastDate;

    @Column(name = "price", nullable = false)
    private Integer price;

//    글 생성, 수정 시간 추가
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardImage> images = new ArrayList<>();

    @Builder
    public Board(String content, String region, Date startDate, Date lastDate, Integer price, List<BoardImage> images) {

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
    }

//    글 수정
    public void update(String content, String region, Date startDate, Date lastDate, Integer price) {

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

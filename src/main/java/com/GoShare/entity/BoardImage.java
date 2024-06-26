package com.GoShare.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "board_img")
@Getter
@NoArgsConstructor
public class BoardImage {

    @Id
    @Column(name = "img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName;  //이미지 파일명

    private String oriImgName;  //원본 이미지 파일명

    private String imgUrl;  //이미지 조회 경로

    private String repImgYn;  //대표 이미지 여부

    //보드 엔티티와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardImage(String imgName, String oriImgName, String imgUrl, String repImgYn){
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    //대표 이미지 설정
    public void setRepImgYn(String repImgYn){
        this.repImgYn = repImgYn;
    }

    //이미지 수정
    public void updateImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}

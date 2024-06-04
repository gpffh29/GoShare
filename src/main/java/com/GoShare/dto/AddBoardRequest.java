package com.GoShare.dto;

import com.GoShare.entity.Board;
import com.GoShare.entity.BoardImage;
import com.GoShare.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddBoardRequest {

    private String content;
    private String region;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date lastDate;
    private Integer price;

    //이미지 추가
    private List<BoardImgRequest> images;

    public Board toEntity(Member member){

        //BoardImgRequest 리스트 BoardImage 리스트로 변환
        List<BoardImage> boardImages = images.stream()
                .map(BoardImgRequest::toEntity)
                .collect(Collectors.toList());

        Board board = Board.builder()
                .member(member)
                .content(content)
                .region(region)
                .startDate(startDate)
                .lastDate(lastDate)
                .price(price)
                .images(boardImages)
                .member(member)
                .build();

        //각 이미지에 Board 설정
        boardImages.forEach(image -> image.setBoard(board));

        return board;
    }
}

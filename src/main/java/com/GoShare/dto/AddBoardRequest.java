package com.GoShare.dto;

import com.GoShare.entity.Board;
import com.GoShare.entity.BoardImage;
import com.GoShare.entity.Car;
import com.GoShare.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddBoardRequest {

    private String content;
    private String region;
    private LocalDate startDate;
    private LocalDate lastDate;
    private Integer price;
    private Long car_id;

    //이미지 추가
    private List<BoardImgRequest> images;

    public Board toEntity(Member member, Optional<Car> car){

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
                .carModel(car.get().getCar_model())
                .carName(car.get().getCar_name())
                .carType(car.get().getCar_type())
                .build();

        //각 이미지에 Board 설정
        boardImages.forEach(image -> image.setBoard(board));

        return board;
    }
}

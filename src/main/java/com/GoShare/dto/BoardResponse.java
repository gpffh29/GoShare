package com.GoShare.dto;


import com.GoShare.entity.Board;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//get 요청이 오면 응답을 위한 DTO
@Getter
public class BoardResponse {


    private final String content;
    private final String region;
    private final LocalDate startDate;
    private final LocalDate lastDate;
    private final Integer price;

    private final List<BoardImgResponse> images;

    public BoardResponse(Board board){

        this.content = board.getContent();
        this.region = board.getRegion();
        this.startDate = board.getStartDate();
        this.lastDate = board.getLastDate();
        this.price = board.getPrice();
        this.images = board.getImages().stream()
                .map(BoardImgResponse::new)
                .collect(Collectors.toList());
    }

}

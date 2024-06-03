package com.GoShare.dto;


import com.GoShare.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//get 요청이 오면 응답을 위한 DTO
@Getter
public class BoardResponse {


    private final String content;
    private final String region;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private final Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private final Date lastDate;
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

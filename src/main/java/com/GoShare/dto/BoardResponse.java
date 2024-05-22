package com.GoShare.dto;


import com.GoShare.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;

//get 요청이 오면 응답을 위한 DTO
@Getter
public class BoardResponse {

    private final String carImg;
    private final String content;
    private final String region;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private final Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private final Date lastDate;
    private final Integer price;

    public BoardResponse(Board board){
        this.carImg = board.getCarImg();
        this.content = board.getContent();
        this.region = board.getRegion();
        this.startDate = board.getStartDate();
        this.lastDate = board.getLastDate();
        this.price = board.getPrice();
    }

}

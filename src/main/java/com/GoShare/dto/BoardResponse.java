package com.GoShare.dto;


import com.GoShare.entity.Board;

import java.util.Date;

//get 요청이 오면 응답을 위한 DTO
public class BoardResponse {

    private final String carImg;
    private final String content;
    private final String region;
    private final Date startDate;
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

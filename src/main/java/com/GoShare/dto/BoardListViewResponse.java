package com.GoShare.dto;


//뷰에 데이터 전달을 위한 객체

import com.GoShare.entity.Board;
import lombok.Getter;

import java.util.Date;

@Getter
public class BoardListViewResponse {

    private final Date startDate;

    private final Date lastDate;

    private final Integer price;

    public BoardListViewResponse(Board board) {

        this.startDate = board.getStartDate();
        this.lastDate = board.getLastDate();
        this.price = board.getPrice();
    }
}

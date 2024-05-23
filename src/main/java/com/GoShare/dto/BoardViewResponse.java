package com.GoShare.dto;


import com.GoShare.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

//뷰에서 사용할 DTO
@NoArgsConstructor
@Getter
public class BoardViewResponse {

    private Long id;

    private String region;
    private Date startDate;
    private Date lastDate;
    private Integer price;
    private String content;

    private LocalDateTime createdAt;

    public BoardViewResponse(Board board){
        this.id = board.getId();
        this.region = board.getRegion();
        this.startDate = board.getStartDate();
        this.lastDate = board.getLastDate();
        this.price = board.getPrice();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
    }
}

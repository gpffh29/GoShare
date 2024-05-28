package com.GoShare.dto;


import com.GoShare.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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

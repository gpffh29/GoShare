package com.GoShare.dto;

import com.GoShare.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddBoardRequest {

    private String carImg;
    private String content;
    private String region;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date lastDate;
    private Integer price;

    public Board toEntity(){
        return Board.builder()
                .carImg(carImg)
                .content(content)
                .region(region)
                .startDate(startDate)
                .lastDate(lastDate)
                .price(price)
                .build();
    }
}

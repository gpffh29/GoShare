package com.GoShare.dto;

import com.GoShare.entity.Board;
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
    private Date startDate;
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

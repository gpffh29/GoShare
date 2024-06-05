package com.GoShare.dto;


//뷰에 데이터 전달을 위한 객체

import com.GoShare.entity.Board;
import com.GoShare.entity.BoardImage;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BoardListViewResponse {

    private final Long id;

    private final LocalDate startDate;

    private final LocalDate lastDate;

    private final Integer price;

    private final String repImgUrl;

    public BoardListViewResponse(Board board) {
        
        this.id = board.getId();
        this.startDate = board.getStartDate();
        this.lastDate = board.getLastDate();
        this.price = board.getPrice();
        this.repImgUrl = getRepImgUrl();
    }

    private String getRepImgUrl(Board board) {
        for (BoardImage image : board.getImages()) {
            if("Y".equals(image.getRepImgYn())){
                return image.getImgUrl();
            }
        }
        return null;
    }
}

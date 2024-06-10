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

    private final String region;

    private final String car_type;

    public BoardListViewResponse(Board board) {
        
        this.id = board.getId();
        this.startDate = board.getStartDate();
        this.lastDate = board.getLastDate();
        this.price = board.getPrice();
        this.repImgUrl = getRepImgUrl(board);
        this.region = board.getRegion();
        this.car_type=board.getCarType();
    }

    public String getRepImgUrl(Board board) {
        for (BoardImage image : board.getImages()) {
            if("Y".equals(image.getRepImgYn())){
                return image.getImgUrl();
            }
        }
        return null;
    }
}

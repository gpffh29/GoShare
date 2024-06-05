package com.GoShare.dto;


import com.GoShare.entity.Board;
import com.GoShare.entity.BoardImage;
import com.GoShare.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private String member_email;

    private LocalDateTime createdAt;

    private String repImgUrl;
    private List<BoardImgResponse> images;

    public BoardViewResponse(Board board){
        this.id = board.getId();
        this.region = board.getRegion();
        this.startDate = board.getStartDate();
        this.lastDate = board.getLastDate();
        this.price = board.getPrice();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.images = board.getImages().stream()
                .map(BoardImgResponse::new)
                .collect(Collectors.toList());
        this.repImgUrl = getRepImgUrl();
        this.member_email=board.getMember().getEmail();
    }

    private String getRepImgUrl(Board board){
        return board.getImages().stream()
                .filter(image -> "y".equals(image.getRepImgYn()))
                .map(BoardImage::getImgUrl)
                .findFirst()
                .orElse(null);
    }
}

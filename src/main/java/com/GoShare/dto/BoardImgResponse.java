package com.GoShare.dto;

import com.GoShare.entity.BoardImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardImgResponse {
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;

    public BoardImgResponse(BoardImage boardImage) {
        this.imgName = boardImage.getImgName();
        this.oriImgName = boardImage.getOriImgName();
        this.imgUrl = boardImage.getImgUrl();
        this.repImgYn = boardImage.getRepImgYn();
    }
}

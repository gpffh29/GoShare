package com.GoShare.dto;


import com.GoShare.entity.BoardImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardImgRequest {

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    public BoardImage toEntity() {
        return BoardImage.builder()
                .imgName(imgName)
                .oriImgName(oriImgName)
                .imgUrl(imgUrl)
                .repImgYn(repImgYn)
                .build();
    }
}

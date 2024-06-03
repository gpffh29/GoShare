package com.GoShare.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//글 수정 DTO
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateBoardRequest {
    private String content;
    private String region;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date lastDate;
    private Integer price;

    private List<BoardImgRequest> images;

}

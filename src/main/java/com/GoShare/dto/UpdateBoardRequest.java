package com.GoShare.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

//글 수정 DTO
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateBoardRequest {
    private String content;
    private String region;
    private LocalDate startDate;
    private LocalDate lastDate;
    private Integer price;

    private List<BoardImgRequest> images;

}

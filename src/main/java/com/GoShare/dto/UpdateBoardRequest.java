package com.GoShare.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

//글 수정 DTO
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateBoardRequest {
    private String content;
    private String region;
    private Date startDate;
    private Date lastDate;
    private Integer price;
}

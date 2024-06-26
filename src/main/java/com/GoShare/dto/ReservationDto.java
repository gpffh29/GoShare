package com.GoShare.dto;

import com.GoShare.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDto {
    private String owner;
    private String startDate;
    private String lastDate;
    private String loaner;
    private String carName;
    private Long board_id;

    public ReservationDto(Reservation reservation) {
    }
}

package com.GoShare.controller;

import com.GoShare.dto.ReservationDto;
import com.GoShare.service.EmailService;
import com.GoShare.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final EmailService emailService;
    private final ReservationService reservationService;

    @PostMapping(value = "/reservation")
    public String SendEmail(ReservationDto reservationDto){
        reservationService.saveReservation(reservationDto);
        emailService.sendSimpleEmail(reservationDto);
        return "redirect:/boards";
    }
}

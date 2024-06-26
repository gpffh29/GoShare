package com.GoShare.controller;

import com.GoShare.dto.ReservationDto;
import com.GoShare.entity.Member;
import com.GoShare.entity.Reservation;
import com.GoShare.service.EmailService;
import com.GoShare.service.MemberService;
import com.GoShare.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final EmailService emailService;
    private final ReservationService reservationService;
    private final MemberService memberService;

    @PostMapping(value = "/reservation")
    public String SendEmail(ReservationDto reservationDto, Model model){
        reservationService.saveReservation(reservationDto);
        emailService.sendSimpleEmail(reservationDto);
        model.addAttribute("reservation", reservationDto);
        return "reservation/reservationSuccess";
    }

    @GetMapping(value = "/reservation/status")
    public String status(Model model){

        //현재 세션에 있는 회원 id 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String member_email = (String) authentication.getName();
        String name = memberService.findMemberByEmail(member_email).getName();
        model.addAttribute("name", name);

        model.addAttribute("reservation_G", reservationService.findOwnerReservation(member_email));
        model.addAttribute("reservation_S", reservationService.findByLoaner(member_email));

        return "reservation/Myreservation";
    }

    @DeleteMapping(value = "/reservation/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        emailService.cancel_reservation(id);
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}

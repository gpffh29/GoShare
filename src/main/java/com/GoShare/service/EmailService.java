package com.GoShare.service;

import com.GoShare.dto.ReservationDto;
import com.GoShare.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendSimpleEmail(ReservationDto reservationDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String rental_email = (String) authentication.getName();

        SimpleMailMessage message = new SimpleMailMessage();
        String owning_email= reservationDto.getOwning_email();
        message.setTo(owning_email);
        message.setSubject("[GoShare] 차량 예약 요청 메일입니다");
        String start_date=reservationDto.getStartDate();
        String last_date=reservationDto.getLastDate();

        message.setText("예약자 이메일 : "+rental_email+"\n대여 시작일 : "+start_date+"\n대여 종료일 : "+last_date);

        mailSender.send(message);
    }
}
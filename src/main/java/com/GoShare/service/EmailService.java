package com.GoShare.service;

import com.GoShare.dto.ReservationDto;
import com.GoShare.entity.Member;
import com.GoShare.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final ReservationService reservationService;

    public void sendSimpleEmail(ReservationDto reservationDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String rental_email = (String) authentication.getName();

        SimpleMailMessage message = new SimpleMailMessage();
        String owning_email= reservationDto.getOwner();
        message.setTo(owning_email);
        message.setSubject("[GoShare] 차량 예약 요청 메일입니다");
        String start_date=reservationDto.getStartDate();
        String last_date=reservationDto.getLastDate();
        String carName=reservationDto.getCarName();

        message.setText("예약자 이메일 : "+rental_email+"\n차종 : "+carName+"\n대여 시작일 : "+start_date+"\n대여 종료일 : "+last_date+"\nhttp://210.178.50.79/GoShare/reservation/status");

        mailSender.send(message);
    }

    public void cancel_reservation(Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);

        SimpleMailMessage message1 = new SimpleMailMessage();
        String owning_email= reservation.get().getOwner();
        message1.setTo(owning_email);
        message1.setSubject("[GoShare] 차량 예약 취소 메일입니다");
        String start_date=reservation.get().getStartDate();
        String last_date=reservation.get().getLastDate();
        String carName=reservation.get().getCarName();

        message1.setText("차종 : "+carName+"\n대여 시작일 : "+start_date+"\n대여 종료일 : "+last_date+ "\n해당 예약이 취소되었습니다."+"\nhttp://210.178.50.79/GoShare/reservation/status");

        mailSender.send(message1);
    }
}
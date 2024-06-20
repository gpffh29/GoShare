package com.GoShare.service;

import com.GoShare.dto.ReservationDto;
import com.GoShare.entity.Member;
import com.GoShare.entity.Reservation;
import com.GoShare.repository.MemberRepository;
import com.GoShare.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    public void saveReservation(ReservationDto reservationDto) {
        Member member = memberRepository.findByEmail(reservationDto.getLoaner());
        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setOwner(reservationDto.getOwner());
        reservation.setLoaner(reservationDto.getLoaner());
        reservation.setStartDate(reservationDto.getStartDate());
        reservation.setLastDate(reservationDto.getLastDate());
        reservation.setCarName(reservationDto.getCarName());

        reservationRepository.save(reservation);
    }

    public Optional<Reservation> findReservation() {
        //현재 세션에 있는 회원 id 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String member_email = (String) authentication.getName();
        return reservationRepository.findById(memberRepository.findByEmail(member_email).getId());
    }
}

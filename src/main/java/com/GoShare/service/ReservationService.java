package com.GoShare.service;

import com.GoShare.dto.ReservationDto;
import com.GoShare.entity.Member;
import com.GoShare.entity.Reservation;
import com.GoShare.repository.MemberRepository;
import com.GoShare.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    public void saveReservation(ReservationDto reservationDto) {
        Member member = memberRepository.findByEmail(reservationDto.getOwner());
        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setOwner(reservationDto.getOwner());
        reservation.setLoaner(reservationDto.getLoaner());
        reservation.setStartDate(reservationDto.getStartDate());
        reservation.setLastDate(reservationDto.getLastDate());

        reservationRepository.save(reservation);
    }
}

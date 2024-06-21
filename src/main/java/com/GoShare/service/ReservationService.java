package com.GoShare.service;

import com.GoShare.dto.ReservationDto;
import com.GoShare.entity.Member;
import com.GoShare.entity.Reservation;
import com.GoShare.repository.MemberRepository;
import com.GoShare.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final BoardService boardService;

    public void saveReservation(ReservationDto reservationDto) {
        Member member = memberRepository.findByEmail(reservationDto.getLoaner());
        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setOwner(reservationDto.getOwner());
        reservation.setLoaner(reservationDto.getLoaner());
        reservation.setStartDate(reservationDto.getStartDate());
        reservation.setLastDate(reservationDto.getLastDate());
        reservation.setCarName(reservationDto.getCarName());
        reservation.setBoard(boardService.findById(reservationDto.getBoard_id()));

        reservationRepository.save(reservation);
    }

    public List<Reservation> findOwnerReservation(String owner) {
        return reservationRepository.findByOwner(owner);
    }

    public List<Reservation> findByLoaner(String loaner) {
        return reservationRepository.findByLoaner(loaner);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }
}

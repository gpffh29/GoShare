package com.GoShare.repository;

import com.GoShare.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT p FROM Board p WHERE " +
            "(COALESCE(:region, '') = '' OR p.region LIKE %:region%) AND " +
            "(COALESCE(:car_type, '') = '' OR p.carType = :car_type) AND " +
            "(COALESCE(:startDate, null) IS NULL OR p.startDate <= :startDate) AND " +
            "(COALESCE(:endDate, null) IS NULL OR p.lastDate >= :endDate)")
    Page<Board> findByCriteria(@Param("region") String region,
                               @Param("car_type") String car_type,
                               @Param("startDate") LocalDate startDate,
                               @Param("endDate") LocalDate endDate,
                               Pageable pageable);
}

package com.GoShare.repository;

import com.GoShare.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository<BoardImage, Long> {
    List<BoardImage> deleteAllByBoardId(Long boardId);

}

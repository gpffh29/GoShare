package com.GoShare.controller;


import com.GoShare.dto.BoardListViewResponse;
import com.GoShare.dto.BoardViewResponse;
import com.GoShare.dto.ReservationDto;
import com.GoShare.entity.Board;
import com.GoShare.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//글 전체 리스트를 담은 뷰 반환
@RequiredArgsConstructor
@Controller
public class BoardViewController {

    private final BoardService boardService;

    //    메인 보드
    @GetMapping("/boards")
    public String getBoards(Model model) {
        List<BoardListViewResponse> boards = boardService.findAll().stream()
                .map(BoardListViewResponse::new)
                .toList();

        model.addAttribute("boards", boards);

//        mainBoard.html
        return "boards/mainBoard";
    }

//    글 상세 페이지
    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", new BoardViewResponse(board));
        model.addAttribute("Reservation", new ReservationDto());
        return "boards/board";
    }

    //    글 수정, 생성 페이지
    @GetMapping("/boards/post")
    public String newBoard(@RequestParam(required = false) Long id, Model model) {
//        id가 없으면 생성
        if (id == null) {
            model.addAttribute("board", new BoardViewResponse());
        } else {  //id가 있으면 수정
            Board board = boardService.findById(id);
            model.addAttribute("board", new BoardViewResponse(board));
        }

        return "boards/newBoard";
    }
}
package com.GoShare.controller;


import com.GoShare.dto.BoardListViewResponse;
import com.GoShare.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//글 전체 리스트를 담은 뷰 반환
@RequiredArgsConstructor
@Controller
public class BoardViewController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public String getBoards(Model model){
        List<BoardListViewResponse> boards = boardService.findAll().stream()
                .map(BoardListViewResponse::new)
                .toList();

        model.addAttribute("boards", boards);

//        mainBoard.html
        return "boards/mainBoard";
    }
}

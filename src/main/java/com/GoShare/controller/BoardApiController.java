package com.GoShare.controller;


import com.GoShare.dto.AddBoardRequest;
import com.GoShare.dto.BoardResponse;
import com.GoShare.entity.Board;
import com.GoShare.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/board/post")
    public ResponseEntity<Board> addBoard(@RequestBody AddBoardRequest request){
        Board savedBoard = boardService.save(request);

        //요청이 성공적이면 저장된 글 정보를 응답 객체에 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBoard);
    }

    @GetMapping("/api/boards")
    public ResponseEntity<List<BoardResponse>> findAllBoards() {
        List<BoardResponse> boards = boardService.findAll()
                .stream()
                .map(BoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }
}

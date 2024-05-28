package com.GoShare.controller;


import com.GoShare.dto.AddBoardRequest;
import com.GoShare.dto.BoardResponse;
import com.GoShare.dto.UpdateBoardRequest;
import com.GoShare.entity.Board;
import com.GoShare.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

//   글 작성
    @PostMapping("/api/boards")
    public ResponseEntity<Board> addBoard(@RequestBody AddBoardRequest request){
        Board savedBoard = boardService.save(request);
        System.out.println("test /api/boards");

        //요청이 성공적이면 저장된 글 정보를 응답 객체에 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBoard);
    }

//    메인 보드
    @GetMapping("/api/boards")
    public ResponseEntity<List<BoardResponse>> findAllBoards() {
        System.out.println("test /api/boards");
        List<BoardResponse> boards = boardService.findAll()
                .stream()
                .map(BoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }

//    특정 글 보기
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<BoardResponse> findBoardById(@PathVariable long id){
        Board board = boardService.findById(id);

        return ResponseEntity.ok().body(new BoardResponse(board));
    }


//    글 삭제
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id){
        boardService.delete(id);

        return ResponseEntity.ok()
                .build();
    }


//    글 수정
    @PutMapping("/api/boards/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable long id, @RequestBody UpdateBoardRequest request){
        Board updatedBoard = boardService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedBoard);
    }
}

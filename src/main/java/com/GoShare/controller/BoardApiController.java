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
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLOutput;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

//   글 작성
    @PostMapping("/api/boards")
    public ResponseEntity<Board> addBoard(@RequestPart("request") AddBoardRequest request,
                                          @RequestPart("images") List<MultipartFile> images){
        System.out.println("test /api/boards");
        try{
            Board savedBoard = boardService.save(request, images);
            System.out.println("test /api/boards");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBoard);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

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
    public ResponseEntity<Board> updateBoard(@PathVariable long id, @RequestPart("request") UpdateBoardRequest request,
                                             @RequestPart("images") List<MultipartFile> images){
        try{
            Board updatedBoard = boardService.update(id, request, images);
            return ResponseEntity.ok().body(updatedBoard);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

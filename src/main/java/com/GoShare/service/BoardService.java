package com.GoShare.service;


import com.GoShare.dto.AddBoardRequest;
import com.GoShare.dto.UpdateBoardRequest;
import com.GoShare.entity.Board;
import com.GoShare.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

//    글 추가 메서드
    public Board save(AddBoardRequest request){
        System.out.println(request+"글 등록 확인 텍스트");
        return boardRepository.save(request.toEntity());
    }

//    데이터베이스에 저장되어 있는 글을 모두 가져오는 findAll()메서드
    public List<Board> findAll(){
        System.out.println("글 불러오는 텍스트");
        return boardRepository.findAll();
    }

//    글 하나를 조회하는 findById() 메서드
    public Board findById(long id){
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id)); //존재하지 않을 시 예외 처리

    }

//    글을 삭제하는 delete() 메서드
    public void delete(long id){
        boardRepository.deleteById(id);
    }

//    글을 업데이트하는 update() 메서드
    @Transactional
    public Board update(long id, UpdateBoardRequest request){
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        board.update(request.getCarImg(), request.getContent(), request.getRegion(), request.getStartDate(), request.getLastDate(), request.getPrice());

        return board;
    }
}

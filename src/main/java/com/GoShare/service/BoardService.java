package com.GoShare.service;


import com.GoShare.dto.AddBoardRequest;
import com.GoShare.entity.Board;
import com.GoShare.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

//    글 추가 메서드
    public Board save(AddBoardRequest request){
        return boardRepository.save(request.toEntity());
    }

//    데이터베이스에 저장되어 있는 글을 모두 가져오는 findAll()메서드
    public List<Board> findAll(){
        return boardRepository.findAll();
    }
}

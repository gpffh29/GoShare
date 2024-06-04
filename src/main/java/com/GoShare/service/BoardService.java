package com.GoShare.service;


import com.GoShare.dto.AddBoardRequest;
import com.GoShare.dto.BoardImgRequest;
import com.GoShare.dto.UpdateBoardRequest;
import com.GoShare.entity.Board;
import com.GoShare.entity.BoardImage;
import com.GoShare.entity.Member;
import com.GoShare.repository.BoardRepository;
import com.GoShare.repository.ImgRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ImgRepository imgRepository;
    private final BoardImageService boardImageService;
    private final MemberService memberService;

//    글 추가 메서드
    public Board save(AddBoardRequest request, List<MultipartFile> imgFileList) throws Exception{
//        System.out.println("save method");  //메소드 실행 확인 test

        //현재 세션에 있는 회원 id 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String member_email = (String) authentication.getName();
        Member member = memberService.findMemberByEmail(member_email);

        Board board = request.toEntity(member);
        boardRepository.save(board);
        //이미지 등록
        for(int i=0; i< imgFileList.size(); i++){
            BoardImage boardImage = new BoardImage();
            boardImage.setBoard(board);
            if(i == 0)
                boardImage.setRepImgYn("Y");
            else
                boardImage.setRepImgYn("N");

            boardImageService.saveImage(boardImage, imgFileList.get(i));

            imgRepository.save(boardImage);
        }

        return board;
    }

//    데이터베이스에 저장되어 있는 글을 모두 가져오는 findAll()메서드
    public List<Board> findAll(){
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

////    글을 업데이트하는 update() 메서드
    @Transactional
    public Board update(long id, UpdateBoardRequest request, List<MultipartFile> imgFileList) throws Exception{
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        board.update(request.getContent(), request.getRegion(), request.getStartDate(), request.getLastDate(), request.getPrice());

        //기존 이미지 삭제 및 새로운 이미지 등록
        imgRepository.deleteAllByBoardId(board.getId());
        for(int i=0; i< imgFileList.size(); i++){
            MultipartFile imgFile = imgFileList.get(i);

            BoardImage boardImage = new BoardImage();
            boardImage.setBoard(board);
            if(i==0){
                boardImage.setRepImgYn("Y");
            }else {
                boardImage.setRepImgYn("N");
            }
            boardImageService.saveImage(boardImage, imgFile);
            imgRepository.save(boardImage);
        }

        return board;
    }
}

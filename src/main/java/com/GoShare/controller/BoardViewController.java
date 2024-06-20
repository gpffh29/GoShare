package com.GoShare.controller;


import com.GoShare.dto.BoardListViewResponse;
import com.GoShare.dto.BoardViewResponse;
import com.GoShare.dto.ReservationDto;
import com.GoShare.entity.Board;
import com.GoShare.entity.Car;
import com.GoShare.entity.Member;
import com.GoShare.service.BoardService;
import com.GoShare.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final MemberService memberService;

    //    메인 보드
    @GetMapping("/boards")
    public String getBoards(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size) {
//        List<BoardListViewResponse> boards = boardService.findAll().stream()
//                .map(BoardListViewResponse::new)
//                .toList();
//        model.addAttribute("boards", boards);
        //페이징 처리
        Page<Board> boardPage = boardService.getBoards(page, size);
        List<BoardListViewResponse> boards = boardPage.stream()
                .map(BoardListViewResponse::new)
                .toList();
        model.addAttribute("boards", boards);
        model.addAttribute("boardPage", boardPage);
        model.addAttribute("currentPage", page);

//        mainBoard.html
        return "boards/mainBoard";
    }

//    글 상세 페이지
    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);
        //현재 세션에 있는 회원 id 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String member_email = (String) authentication.getName();
        String member_email = null;
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            member_email = (String) authentication.getName();
        }
        model.addAttribute("Authentication", member_email);
        model.addAttribute("board", new BoardViewResponse(board));
        model.addAttribute("Reservation", new ReservationDto());
        return "boards/board";
    }

    //    글 수정, 생성 페이지
    @GetMapping("/boards/post")
    public String newBoard(@RequestParam(required = false) Long id, Model model) {
//        id가 없으면 생성
        if (id == null) {
            //현재 세션에 있는 회원 id 가져오기
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String member_email = (String) authentication.getName();
            Member member=memberService.findMemberByEmail(member_email);

            //user가 차량을 소지하지 않으면 차량 등록페이지로 리다이렉션
            if(member.getCars().isEmpty()) {
                model.addAttribute("EmptyCar", "등록된 차량이 존재하지 않습니다 차량을 먼저 등록해주세요!");
            }

            List<Car> cars = member.getCars();

            model.addAttribute("cars", cars);
            model.addAttribute("board", new BoardViewResponse());
        } else {  //id가 있으면 수정
            Board board = boardService.findById(id);
            model.addAttribute("board", new BoardViewResponse(board));
        }

        return "boards/newBoard";
    }
}
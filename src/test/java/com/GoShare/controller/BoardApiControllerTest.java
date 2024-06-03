package com.GoShare.controller;

import com.GoShare.dto.AddBoardRequest;
import com.GoShare.dto.UpdateBoardRequest;
import com.GoShare.entity.Board;
import com.GoShare.repository.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//글 등록 테스트
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class BoardApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        boardRepository.deleteAll();

        // ObjectMapper 설정
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    @DisplayName("addBoard: 글 추가에 성공")
    @Test
    public void addBoard() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String url = "/api/boards";
        final String content = "content";
        final String region = "region";
        final Date startDate = dateFormat.parse("2024-05-06");
        final Date lastDate = dateFormat.parse("2024-05-10");
        final Integer price = 50000;
        final AddBoardRequest userRequest =new AddBoardRequest(content,region,startDate,lastDate,price);


//        json으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        ResultActions result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));

        result.andExpect(status().isCreated());

        List<Board> boards = boardRepository.findAll();

        assertEquals(1, boards.size());
        assertEquals(content, boards.get(0).getContent());
        assertEquals(region, boards.get(0).getRegion());
        assertEquals(startDate, boards.get(0).getStartDate());
        assertEquals(lastDate, boards.get(0).getLastDate());
        assertEquals(price, boards.get(0).getPrice());

    }

    @DisplayName("findAllBoards: 글 목록 조회에 성공")
    @Test
    public void findAllBoards() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String url = "/api/boards";

        final String content = "content";
        final String region = "region";
        final Date startDate = dateFormat.parse("2024-05-06");
        final Date lastDate = dateFormat.parse("2024-05-10");
        final Integer price = 50000;

        boardRepository.save(Board.builder().content(content).region(region).startDate(startDate).lastDate(lastDate).price(price).build());

        final ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(price))
                .andExpect(jsonPath("$[0].lastDate").value(dateFormat.format(lastDate)))
                .andExpect(jsonPath("$[0].startDate").value(dateFormat.format(startDate)))
                .andExpect(jsonPath("$[0].region").value(region))
                .andExpect(jsonPath("$[0].content").value(content));
    }

    @DisplayName("findBoard: 글 조회에 성공")
    @Test
    public void findBoard() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String url = "/api/boards/{id}";
        final String content = "content";
        final String region = "region";
        final Date startDate = dateFormat.parse("2024-05-06");
        final Date lastDate = dateFormat.parse("2024-05-10");
        final Integer price = 50000;

        Board savedBoard = boardRepository.save(Board.builder().content(content).region(region).startDate(startDate).lastDate(lastDate).price(price).build());


        final ResultActions resultActions = mockMvc.perform(get(url, savedBoard.getId()));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.lastDate").value(dateFormat.format(lastDate)))
                .andExpect(jsonPath("$.startDate").value(dateFormat.format(startDate)))
                .andExpect(jsonPath("$.region").value(region))
                .andExpect(jsonPath("$.content").value(content));

    }

    @DisplayName("deleteBoard: 글 삭제에 성공")
    @Test
    public void deleteBoard() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String url = "/api/boards/{id}";

        final String content = "content";
        final String region = "region";
        final Date startDate = dateFormat.parse("2024-05-06");
        final Date lastDate = dateFormat.parse("2024-05-10");
        final Integer price = 50000;

        Board savedBoard = boardRepository.save(Board.builder().content(content).region(region).startDate(startDate).lastDate(lastDate).price(price).build());

        mockMvc.perform(delete(url, savedBoard.getId())).andExpect(status().isOk());

        List<Board> boards = boardRepository.findAll();

        assertEquals(0, boards.size());

    }

    @DisplayName("updateBoard: 글 수정에 성공")
    @Test
    public void updateBoard() throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String url = "/api/boards/{id}";
        final String content = "content";
        final String region = "region";
        final Date startDate = dateFormat.parse("2024-05-06");
        final Date lastDate = dateFormat.parse("2024-05-10");
        final Integer price = 50000;


        Board savedBoard = boardRepository.save(Board.builder().content(content).region(region).startDate(startDate).lastDate(lastDate).price(price).build());

        final String newCarImg = "newCarImg";
        final String newContent = "newContent";
        final String newRegion = "newRegion";
        final Date newStartDate = dateFormat.parse("2024-05-07");
        final Date newLastDate = dateFormat.parse("2024-05-15");
        final Integer newPrice = 100000;

        UpdateBoardRequest request = new UpdateBoardRequest(newContent, newRegion, newStartDate, newLastDate, newPrice);

        ResultActions result = mockMvc.perform(put(url, savedBoard.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        Board board = boardRepository.findById(savedBoard.getId()).get();

        assertEquals(board.getContent(), newContent);
        assertEquals(board.getRegion(), newRegion);
        assertEquals(board.getStartDate(), newStartDate);
        assertEquals(board.getLastDate(), newLastDate);
        assertEquals(board.getPrice(), newPrice);
    }

}
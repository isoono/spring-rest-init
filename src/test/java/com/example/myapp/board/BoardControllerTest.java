package com.example.myapp.board;

import com.example.myapp.board.model.BoardEntity;
import com.example.myapp.board.model.BoardParam;
import com.example.myapp.board.repository.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BoardControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  BoardRepository boardRepository;

  @Autowired
  ObjectMapper objectMapper;

  @Before
  public void insertBoard(){
    for(int i =0; i<10; i++){
      BoardEntity board = new BoardEntity();
      board.setContent("내용"+i);
      board.setTitle("제목"+i);
      board.setUsername("코딩하는흑구");
      boardRepository.save(board);
    }
  }

  @Test
  public void addBoard() throws Exception {
    BoardParam param = BoardParam.builder()
        .content("추가내용")
        .title("추가제목")
        .username("추가유저")
        .build();

    mockMvc.perform(post("/api/boards/add")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(param)))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    this.getBoard();
  }

  @Test
  public void editBoard() throws Exception{
    BoardParam param = BoardParam.builder()
        .content("수정내용")
        .title("수정제목")
        .username("수정유저")
        .build();

    mockMvc.perform(put("/api/boards/3")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(param)))
        .andDo(print())
        .andExpect(status().isOk());
    this.getBoard();
  }

  @Test
  public void getBoard() throws Exception {
    mockMvc.perform(get("/api/boards/all")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void getBoardOne() throws Exception {
    mockMvc.perform(get("/api/boards/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void deleteBoardOne() throws Exception{
    mockMvc.perform(delete("/api/boards/3")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk());
    this.getBoard();
  }
}

package com.example.myapp.home;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void getHome() {
    try {
      mockMvc.perform(get("/api/home/get")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
          .param("name", "soonho")
          .param("age", "27"))
          .andExpect(status().isOk())
          .andDo(print());
    } catch (Exception e) {
      System.out.println("-------------------------");
      System.out.println("-------------------------");
      System.out.println("-------------------------");
      System.out.println("-------------------------");
      e.printStackTrace();
    }
  }

}

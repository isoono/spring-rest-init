package com.example.myapp.board.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardParam {

  @Min(0)
  Long seq;

  @NotEmpty
  String content;

  @NotEmpty
  String username;

  @NotEmpty
  String title;


}

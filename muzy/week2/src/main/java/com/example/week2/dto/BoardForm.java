package com.example.week2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


public class BoardForm {

    @Getter
    @Setter
    @NotEmpty(message = "제목은 필수 입니다.")
    @Size(max = 50, message = "제목은 50자리를 초과할 수 없습니다.")
    private String title;

    @Getter
    @Setter
    @NotEmpty(message = "내용은 필수 입니다.")
    private String content;

    @Getter
    @Setter
    private LocalDateTime createdDate;
    //날짜 자동생성 잘됐는지 확인하기
}

package com.godcoder.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class Board {
    @Id //id를 pk값으로 사용함을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //아이디
    @NotNull
    @Size(min = 2, max = 30, message = "제목은 2자이상 30자 이하입니다.") //2글자 이상 30글자 이하 제한
    private String title; //제목
    private String content; //내용

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}

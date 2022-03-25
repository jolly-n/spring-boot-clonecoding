package com.godcoder.myhome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id //id를 pk값으로 사용함을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name; //권한이름

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}

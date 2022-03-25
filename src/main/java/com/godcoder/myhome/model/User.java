package com.godcoder.myhome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id //id를 pk값으로 사용함을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username; //사용자이름
    private String password; //비밀번호
    private Boolean enabled; //권한상태

    @ManyToMany //다대다 매핑
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}

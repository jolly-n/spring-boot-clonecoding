package com.godcoder.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    /**
     * 서로 연관된 두 테이블 간 조회방법 설정
     * fetch 타입 : EAGER, LAZY(사용될 때 조회)
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //orphanRemoval:부모가 없는 데이터를 삭제
//    @JsonIgnore
    private List<Board> boards = new ArrayList<>();
}

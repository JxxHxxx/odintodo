package jxx.odin.domain.member;


import jxx.odin.domain.character.Character;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Character> characters = new ArrayList<>();

    public Member() {
    }

    public Member(String name) {
        this.name = name;
    }
}

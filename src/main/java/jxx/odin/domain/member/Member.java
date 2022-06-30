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

    private String email;

    private String nickname;

    private String password;

    @OneToMany(mappedBy = "member")
    private List<Character> characters = new ArrayList<>();

    public Member() {
    }

    public Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public Member(String nickname) {
        this.nickname = nickname;
    }

    public Boolean isNotMatched(String password) {
        return !this.password.equals(password);
    }
}

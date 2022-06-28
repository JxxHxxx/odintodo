package jxx.odin.domain.member;

import jxx.odin.domain.character.Character;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Email(message = "이메일을 입력하세요")
    private String email;

    @NotEmpty
    private String nickname;

    @Size(min = 8, max = 16, message = "8 ~ 16자 사이로 입력하세요.")
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Character> characters = new ArrayList<>();

    public Member() {
    }

    public Member(String nickname) {
        this.nickname = nickname;
    }
}

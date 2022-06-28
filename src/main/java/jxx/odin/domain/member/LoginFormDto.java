package jxx.odin.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginFormDto {

    private String email;
    private String password;
    private String name;

    public LoginFormDto() {
    }
}

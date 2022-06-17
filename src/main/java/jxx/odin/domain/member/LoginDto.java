package jxx.odin.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {

    private String name;
    private String password;

    public LoginDto() {
    }
}

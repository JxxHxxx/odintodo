package jxx.odin.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class JoinForm {

    @NotBlank(message = "공백일 수 없습니다.") @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Size(min = 8, max = 16, message = "비밀번호는 8 ~ 16자 입니다.")
    private String password;

    @Size(min = 2, max = 12, message = "닉네임은 2 ~ 12자 입니다.")
    private String nickname;

    public JoinForm() {
    }
}

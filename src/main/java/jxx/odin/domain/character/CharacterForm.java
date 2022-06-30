package jxx.odin.domain.character;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * DTO
 */

@Getter @Setter
public class CharacterForm {

    @Size(min = 2, max = 16, message = "캐릭터명은 2 ~ 16자 사이입니다.")
    private String name;
}

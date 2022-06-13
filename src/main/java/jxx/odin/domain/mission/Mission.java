package jxx.odin.domain.mission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mission {

    private Content content;
    private Boolean complete;

    //테스토 용도 생성자
    public Mission(Content content, Boolean complete) {
        this.content = content;
        this.complete = complete;
    }

    public Mission(Content content) {
        this.content = content;
        this.complete = false;

    }
}

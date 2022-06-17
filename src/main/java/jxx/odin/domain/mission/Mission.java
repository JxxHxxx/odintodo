package jxx.odin.domain.mission;

import jxx.odin.domain.character.Character;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
@Entity
public class Mission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MISSION_NAME")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Content content;
    private Boolean complete = false;

    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID")
    private Character character;

    public Mission() {
    }

    public Mission(Content content) {
        this.content = content;
    }

    public void setCharacter(Character character) {
        this.character = character;
        character.getMissions().add(this);
    }
}

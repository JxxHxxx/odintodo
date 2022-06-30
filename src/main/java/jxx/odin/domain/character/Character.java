package jxx.odin.domain.character;

import jxx.odin.domain.member.Member;
import jxx.odin.domain.mission.Mission;
import jxx.odin.domain.mission.MissionDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Character {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHARACTER_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "character")
    private List<Mission> missions = new ArrayList<>();

    public Character() {
    }
    public Character(String name) {
        this.name = name;
    }

    public Integer missionsSize() {
        return this.missions.size();
    }

    public Mission findMission(Integer index) {
        return this.missions.get(index);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getCharacters().add(this);
    }

    public boolean isMemberOf(Long memberId) {
        return this.getMember().getId().equals(memberId);
    }

    /*public void setMissionComplete(List<Mission> missions) {
        this.missions = missions;
    }*/

}

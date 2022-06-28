package jxx.odin.domain.character;

import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static jxx.odin.domain.member.MemberController.SESSION_MEMBER_ID;

/**
 * 커밋 금지, 개발중
 *
 */

@Slf4j
@Controller
@RequestMapping("/odin/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterRepository characterRepository;
    private final MemberRepository memberRepository;

    @GetMapping()
    public String form(Model model) {

        model.addAttribute("character", new Character());

        return "/character/addForm";
    }

    @PostMapping()
    public String addCharacter(@SessionAttribute(SESSION_MEMBER_ID) Long memberId,
            @ModelAttribute("character") Character character, Model model) {

        Member member = memberRepository.findById(memberId);
        character.setMember(member);

        characterRepository.save(character);

        model.addAttribute("loginMember", member);

        return "/main/loginHome";
    }

    @GetMapping("/{characterId}")
    public String viewMissions(@SessionAttribute(SESSION_MEMBER_ID) Long memberId, @PathVariable(name = "characterId") Long characterId, Model model)  {

        Character character = characterRepository.findById(characterId);

        if (!character.isMemberOf(memberId)) {
            log.info("잘못된 접근입니다.");
            return "redirect:/odin/characters";
        }

        model.addAttribute("character", character);

        character.getMissions()
                .forEach(mission -> log.info("미션 수행 여부 [{}] [{}]",mission.getContent(), mission.getComplete()));
        return "/character/character";
    }

    @PostMapping("/{characterId}")
    public String editMissions(@PathVariable(name = "characterId") Long id, @ModelAttribute("character") CharacterDto characterDto) {

        characterRepository.updateCharacterMission(id, characterDto);

        log.info("캐릭터의 미션 완료 여부를 수정합니다.");

        return "redirect:/odin/character/{characterId}";
    }

}

package jxx.odin.domain.character;

import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static jxx.odin.domain.member.MemberController.SESSION_MEMBER;

/**
 * 커밋 금지, 개발중
 *
 */

@Slf4j
@Controller
@RequestMapping("/odin/character")
@RequiredArgsConstructor
public class characterController {

    private final CharacterManager characterManager;
    private final MemberRepository memberRepository;

    @GetMapping()
    public String form(Model model) {

        model.addAttribute("character", new Character());
        return "/character/addForm";
    }

    @PostMapping()
    public String addCharacter(@SessionAttribute(SESSION_MEMBER) Member sessionMember,
            @ModelAttribute("character") Character character, Model model) {

        Member member = memberRepository.findByMember(sessionMember);
        characterManager.create(member, character);
        memberRepository.update(member);

        model.addAttribute("loginMember", member);

        return "/main/loginHome";
    }
}

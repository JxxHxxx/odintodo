package jxx.odin.domain.member;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/odin")
@RequiredArgsConstructor
public class MemberController {

    public static final String SESSION_MEMBER_ID = "sessionMemberID";
    private final MemberRepository memberRepository;

    @GetMapping()
    public String home(@SessionAttribute(name = SESSION_MEMBER_ID, required = false) Long memberId, Model model) {

        if (memberId == null) {
            model.addAttribute("member", new LoginDto());
            return "/main/home";
        }

        Member loginMember = memberRepository.findById(memberId);
        model.addAttribute("loginMember", loginMember);
        return "/main/loginHome";

    }


    @PostMapping()
    public String login(@Validated @ModelAttribute("member") LoginDto loginDto, BindingResult bindingResult, Model model, HttpServletRequest request) {

        Member loginMember = memberRepository.findByEmail(loginDto.getEmail());

        if (loginMember == null) {
            bindingResult.addError(new ObjectError("member", "아이디/패스워드를 잘못 입력하셨습니다."));
            return "main/home";
        }

        if (loginMember.isNotMatched(loginDto.getPassword())) {
            bindingResult.addError(new ObjectError("member", "아이디/패스워드를 잘못 입력하셨습니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("error = [{}]", bindingResult);
            return "main/home";
        }

        log.info("유저 [{}] 로그인 검증이 완료되었습니다.", loginMember.getNickname());
        model.addAttribute("loginMember", loginMember);

        HttpSession session = request.getSession();
        session.setAttribute(SESSION_MEMBER_ID, loginMember.getId());
        return "redirect:/odin/characters";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/odin";
    }

    @GetMapping("/characters")
    public String viewCharacters(@SessionAttribute(SESSION_MEMBER_ID) Long memberId, Model model) {

        Member loginMember = memberRepository.findById(memberId);
        model.addAttribute("loginMember", loginMember);

        return "/main/loginHome";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "/main/joinForm";
    }

    @PostMapping("/join")
    public String joinV2(@Validated @ModelAttribute("joinForm") JoinForm joinForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("회원 가입 시 잘못된 결과가 존재해서 되돌려 보낸다.");
            return "/main/joinForm";
        }

        Member joinMember = new Member(joinForm.getEmail(), joinForm.getNickname(), joinForm.getPassword());
        memberRepository.save(joinMember);
        log.info("닉네임 명 [{}]의 회원 가입이 완료되었습니다.", joinMember.getNickname());
        return "redirect:/odin";
    }

    //@PostMapping("/join")
    public String join(@ModelAttribute("member") Member member) {
        memberRepository.save(member);
        log.info("유저 [{}]의 회원 가입이 완료되었습니다.", member.getNickname());
        return "redirect:/odin";
    }
}

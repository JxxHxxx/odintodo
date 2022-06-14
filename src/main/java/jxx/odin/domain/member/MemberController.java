package jxx.odin.domain.member;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/odin")
@RequiredArgsConstructor
public class MemberController {

    public static final String SESSION_MEMBER = "sessionMember";
    private final MemberRepository memberRepository;

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("member", new Member());
        return "/main/home";
    }

    @PostMapping()
    public String loginV2(@ModelAttribute("member") Member member, Model model, HttpServletRequest request) {
        //로그인 임시 로직 - 추후 검증 추가 예정
        Member loginMember = memberRepository.findByName(member.getName());
        if (loginMember == null) {
            return "redirect:/odin";
        }

        if (loginMember.getPassword().equals(member.getPassword())) {
            log.info("유저 [{}] 로그인 검증이 완료되었습니다.", loginMember.getName());
        }
        model.addAttribute("loginMember", loginMember);
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_MEMBER, loginMember);

        return "/main/loginHome";
    }

    //@PostMapping()
    public String login(@ModelAttribute("member") Member member, Model model) {
        //로그인 임시 로직 - 추후 검증 추가 예정
        Member loginMember = memberRepository.findByName(member.getName());
        if (loginMember == null) {
            return "redirect:/odin";
        }

        if (loginMember.getPassword().equals(member.getPassword())) {
            log.info("유저 [{}] 로그인 검증이 완료되었습니다.", loginMember.getName());
            model.addAttribute("loginMember", loginMember);
        }
        return "/main/loginHome";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("member", new Member());
        return "/main/joinForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("member") Member member) {
        memberRepository.save(member);
        log.info("유저 [{}]의 회원 가입이 완료되었습니다.", member.getName());
        return "redirect:/odin";
    }
}

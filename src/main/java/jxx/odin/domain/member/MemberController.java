package jxx.odin.domain.member;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    public String login(@ModelAttribute("member") LoginDto loginDto, Model model, HttpServletRequest request) {
        //로그인 임시 로직 - 추후 검증 추가 예정
        log.info("로그인 검증 처리를 시작합니다.");
        Map<String, String> errors = new HashMap<>();

        if (!StringUtils.hasText(loginDto.getEmail())) {
            errors.put("email", "이메일은 필수입니다.");
        }

        if (!StringUtils.hasText(loginDto.getPassword())) {
            errors.put("password", "비밀번호는 필수입니다.");
        }

        if (!errors.isEmpty()) {
            log.info("error = {}", errors);
            model.addAttribute("errors",errors);
            return "main/home";
        }

        Member loginMember = memberRepository.findByEmail(loginDto.getEmail());

        if (loginMember != null) {
            if (!loginMember.getPassword().equals(loginDto.getPassword())) {
                errors.put("global", "이메일/패스워드가 일치하지 않습니다.");
            }
        }

        if (!errors.isEmpty()) {
            log.info("error = {}", errors);
            model.addAttribute("errors",errors);
            return "main/home";
        }

        /*if (loginMember == null) {
            return "redirect:/odin";
        }*/


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

    //@PostMapping()
    public String login(@ModelAttribute("member") Member member, Model model) {
        //로그인 임시 로직 - 추후 검증 추가 예정
        Member loginMember = memberRepository.findByName(member.getNickname());
        if (loginMember == null) {
            return "redirect:/odin";
        }

        if (loginMember.getPassword().equals(member.getPassword())) {
            log.info("유저 [{}] 로그인 검증이 완료되었습니다.", loginMember.getNickname());
            model.addAttribute("loginMember", loginMember);
        }
        return "/main/loginHome";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("member", new LoginFormDto());
        return "/main/joinForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("member") Member member) {
        memberRepository.save(member);
        log.info("유저 [{}]의 회원 가입이 완료되었습니다.", member.getNickname());
        return "redirect:/odin";
    }
}

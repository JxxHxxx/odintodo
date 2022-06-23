package jxx.odin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static jxx.odin.domain.member.MemberController.SESSION_MEMBER_ID;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("인증 체크 인터셉터 실행 URI [{}]", request.getRequestURI());

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SESSION_MEMBER_ID) == null) {

            log.info("미인증 사용자 접근");
            response.sendRedirect("/odin");
            return false;
        }

        return true;
    }
}

package jxx.odin.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {

    }

    @DisplayName("멤버 리파짓토리는 멤버를 저장할 수 있습니다.")
    @Test
    void save() {
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
        Member member = new Member("memberC");

        memberRepository.save(member);


        assertThat(memberRepository.findAll().size()).isEqualTo(3);

    }

    @DisplayName("멤버를 삭제하면 저장 공간안에 해당 멤버가 존재하지 않아야 합니다.")
    @Test
    void delete() {
        Member findMember = memberRepository.findById(0L);

        assertThat(findMember.getNickname()).isEqualTo("memberA");

        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isEqualTo(1);
    }
}
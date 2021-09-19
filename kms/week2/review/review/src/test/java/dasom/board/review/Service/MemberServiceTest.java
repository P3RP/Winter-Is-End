package dasom.board.review.Service;

import dasom.board.review.Domain.Member;
import dasom.board.review.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{

        //Given
        Member member = new Member();
        member.setName("kms");

        //When
        Long saveId = memberService.join(member);

        //Then
        assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception{

        //Given
        Member member1 = new Member();
        member1.setName("kms");

        Member member2 = new Member();
        member2.setName("kms");

        //When
        memberService.join(member1);
        memberService.join(member2); //예외

        //Then
        fail("예외 발생해야한다.");
    }
}
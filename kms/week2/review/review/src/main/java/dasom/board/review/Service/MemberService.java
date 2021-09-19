package dasom.board.review.Service;

import dasom.board.review.Domain.Member;
import dasom.board.review.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//읽기 전용이라는 뜻.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    /*
    //DI
    @Autowired
    MemberRepository memberRepository;
    */

    //lombok을 쓰면 위의 Autowired안 써도 됨.
    private final MemberRepository memberRepository;


    /*
    회원가입
     */
    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /*
    중복 회원 검증
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원임니다.");
        }
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //한 회원 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}

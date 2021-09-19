package dasom.board.review.Repository;

import dasom.board.review.Domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//스프링 빈으로 등록, JPA 예외를 스프링 기반 예외로 예외 변환 -> 무슨 소리임?ㅋㅋ
@Repository
public class MemberRepository {

    // 엔티티 매니저 주입
    @PersistenceContext
    EntityManager em;

    /*
    DB에 member 객체 저장
     */
    public void save(Member member){
        em.persist(member);
    }

    /*
    한 명의 멤버 객체를 찾는다.
     */
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    /*
    멤버 객체 전부 찾는다.
     */
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }


    /*
    이름으로 찾을 것.
     */
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}

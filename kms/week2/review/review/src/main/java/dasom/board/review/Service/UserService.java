package dasom.board.review.Service;

import dasom.board.review.DTO.UserInfoDto;
import dasom.board.review.Domain.UserInfo;
import dasom.board.review.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//읽기 전용이라는 뜻.
//@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    /*
    //DI
    @Autowired
    MemberRepository memberRepository;
    */

    //lombok을 쓰면 위의 Autowired안 써도 됨.
    private final UserRepository userRepository;


    /*
    Spring Security 필수 메소드
    @param id 아이디
    @return UserDetails
    @thorws UsernameNotFoundException 유저가 없을 때 예외
     */

    @Override
    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<UserInfo> findEmail = userRepository.findByEmail(email);
        System.out.println(findEmail + "loadUserByUsername()");
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    /*
    회원정보 저장
    @param infodto 회원정보가 들어있는 DTO
    @return 저장되는 회원의 PK
     */
    public Long save(UserInfoDto infoDto){
        //중복검증
        Optional<UserInfo> byEmail = userRepository.findByEmail(infoDto.getEmail());
        if(!byEmail.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원임니다.");
        }
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        infoDto.setPassword(encode.encode(infoDto.getPassword()));

        return userRepository.save(UserInfo.builder()
                .email(infoDto.getEmail())
                .auth(infoDto.getAuth())
                .password(infoDto.getPassword())
                .build()).getCode();
    }


    //예전 코드

//    public UserInfo findbytoken(String token){
//        System.out.println("find token : " + token);
//        return userRepository.findByToken(token).orElseThrow(()-> new ExpressionException("없는 아이디"));
//    }


    /*
    회원가입
     */
//    @Transactional
//    public Long join(UserInfo member){

//        validateDuplicateMember(member); //중복 회원 검증
//        memberRepository.save(member);
//        return member.getId();
//    }

    /*
    중복 회원 검증
     */
//    private void validateDuplicateMember(UserInfo member) {
//        List<UserInfo> findMembers = memberRepository.findByName(member.getName());
//        if(!findMembers.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 회원임니다.");
//        }
//    }

    /*
    전체 회원 조회
     */
    public List<UserInfo> findMembers(){
        return userRepository.findAll();
    }

    //한 회원 조회
    public UserInfo findOne(String userId){
        return userRepository.findByEmail(userId).orElseThrow(()-> new ExpressionException("없는 아이디"));
    }

    //유저 비밀번호 수정 차후 해야함.
    public void userModify(UserInfoDto userInfoDto){
        UserInfo fuser = findOne(userInfoDto.getEmail());
        //fuser.setPassword(userInfoDto.getPassword());
    }

    //유저 삭제 차후 작성
    public void userDelete(String userid) {
        UserInfo fuser = findOne(userid);
        userRepository.delete(fuser);
    }
}

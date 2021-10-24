package dasom.board.review.Domain;

import javassist.Loader;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

// 접근을 최소화 외부에서 생성은 되지 않는다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter

@Table(name = "userinfo")
public class UserInfo implements UserDetails {
    //strategy ~ -> 데이터베이스에 위임한다는 뜻. 기본키 생성을 DB에 위임
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Long code;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "auth")
    private String auth;

    @Builder
    public UserInfo(String email, String password, String auth){
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    //@OneToMany(mappedBy = "member")
    //private List<Review> reviews = new ArrayList<>();


    //필수적으로 작성해야하는 것들
    //사용자 권한을 콜렉션 형태로 반환
    //단, 클래스 자료형은 GrantedAuthority를 구현해야한다. -> 먼말인지 아직 모름.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        //밑의 로직에서 ,를 기준으로 나누는 이유는 Admin 경우 Admin,User로 두개의 권한을 가지기 때문이라함.
        for(String role : auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        //계정 만료 여부 반환
        //만료 되었는지 로직
        return true; // true -> 아직 만료되지 않았다는 의미
    }

    @Override
    public boolean isAccountNonLocked() {
        //계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않음.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //패스워드 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않음.
    }

    @Override
    public boolean isEnabled() {
        //계정이 사용 가능한지 확인하는 로직
        return true; //true -> 사용가능.
    }
}

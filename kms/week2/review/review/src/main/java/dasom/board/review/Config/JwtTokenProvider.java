package dasom.board.review.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

//참고 https://emgc.tistory.com/133
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String secretKey = "kmsisking";

    //토큰 유효 30분
    private long tokenValidTime = 30 * 60 * 1000L;

    private UserDetailsService userDetailsService;

    //객체 초기화, secretkey를 Base64로 인코딩
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT토큰 생성
    public String createToken(String userPk, String roles){
        Claims claims = Jwts.claims().setSubject(userPk); //JWT payload에 저장되는 저장단위
        claims.put("roles",roles); //정보는 key/value 쌍으로 저장
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)//정보 저장
                .setIssuedAt(now) //토큰발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256,secretKey) // 사용할 알고리즘ㅁ과 signature에 들어갈 secret값 세팅
                .compact();

    }

    //JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    public String jwtgetToken(HttpServletRequest httpServletRequest){
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        String hname = "";
        String hvalue = "";

        while(headerNames.hasMoreElements()){
            hname = headerNames.nextElement();
            hvalue = httpServletRequest.getHeader(hname);
        }
        String[] splitstr = hvalue.split(";");
        String[] str = splitstr[1].split("=");
        String t = str[1];
        return t;
    }
    //토큰에서 회원 정보 추출
    public String getUserPk(String token){

        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //Request의 Header에서 token값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값"
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    //토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch(Exception e ){
            return false;
        }

    }


}

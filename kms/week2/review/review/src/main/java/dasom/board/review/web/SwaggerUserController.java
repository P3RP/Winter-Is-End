package dasom.board.review.web;

import dasom.board.review.DTO.UserInfoDto;
import dasom.board.review.Domain.UserInfo;
import dasom.board.review.Service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//일반 Controller와 다른점은 @ResponseBody가 추가된 것 json형태로 객체 데이터 반환.
//https://mangkyu.tistory.com/49
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SwaggerUserController {
    private final UserService userService;

    @ApiOperation(value = "회원목록", notes = "전체 회원 목록 반환")
    @GetMapping(value = "/users", headers = {"Content-type = application/json"})
    public List<UserInfo> userInfoList(){
        return userService.findMembers();
    }

    /*
    @ApiOperation(value = "회원 등록",notes = "회원의 정보를 받아 DB에 넣는다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Server down"),
            @ApiResponse(code = 500, message = "None Page")
    })
    public List<UserInfo> userInfoList(@RequestBody @ApiParam(value = "회원 한 명 객체", required = true) UserInfoDto userInfoDto){
        userService.save(userInfoDto);
        return userService.findMembers();
    }
     */

    @ApiOperation(value = "회원 정보", notes = "회원 한 명에 대한 정보")
    @GetMapping(value = "/user/{userid}",headers = {"Content-type = application/json"})
    public UserInfo userInfoDto(@PathVariable @ApiParam(value = " 검색할 회원의 아이디") String userid){
        return userService.findOne(userid);
    }

    /*
    @ApiOperation(value = "회원 비밀번호 수정", notes = "회원 비번 수정")
    @PutMapping(value = "/user", headers = {"Content-type1 = application/json"})
    public void userModify(@RequestBody @ApiParam(value = "수정할 회원 객체 ", required = true)UserInfoDto userInfoDto){
        userService.userModify(userInfoDto);
    }
    */
    @ApiOperation(value = "회원 정보 삭제",notes = "회원 정보 삭제")
    @DeleteMapping(value = "/user/{userid}", headers = {"Content-type = application/json"})
    public void userDelete(@PathVariable @ApiParam(value = "삭제할 회원 정보", required = true) String userid){
        userService.userDelete(userid);
    }


}

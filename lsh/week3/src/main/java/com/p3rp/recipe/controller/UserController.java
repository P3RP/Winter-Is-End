package com.p3rp.recipe.controller;

import com.p3rp.recipe.domain.dto.user.RegisterTO;
import com.p3rp.recipe.domain.dto.user.UserTO;
import com.p3rp.recipe.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "User", tags = { "User" })
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @ApiOperation(value = "회원가입", notes = "신규 회원가입")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원가입 완료"),
            @ApiResponse(code = 404, message = "회원가입 경로를 찾을 수 없음"),
            @ApiResponse(code = 409, message = "이미 존재하는 회원 ID"),
    })
    public ResponseEntity<String> register(@RequestBody @ApiParam(name = "registerTO", value = "회원가입 정보", required = true) RegisterTO registerTO) {
        return new ResponseEntity<String>(userService.save(registerTO), null, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    @ApiOperation(value = "전체 회원 목록 검색", notes = "전체 회원의 정보를 검색한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "검색 완료"),
            @ApiResponse(code = 404, message = "검색 경로를 찾을 수 없음"),
    })
    public ResponseEntity<List<UserTO>> retrieveUserAll() {
        return new ResponseEntity<List<UserTO>>(
                userService.getUserAll(), null, HttpStatus.OK
        );
    }

    @GetMapping("/users/{account}")
    @ApiOperation(value = "회원 검색", notes = "아이디로 회원 정보를 검색한다.")
    @ApiImplicitParam(name = "account", value = "아이디", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "검색 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "검색 경로를 찾을 수 없음"),
    })
    public ResponseEntity<UserTO> retrieveUserByAccount(@PathVariable("account") String account) {
        UserTO user = userService.getUserByAccount(account);
        if (user != null) {
            return new ResponseEntity<>(
                    user, null, HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    user, null, HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/users/{account}")
    @ApiOperation(value = "회원 이름 수정", notes = "회원의 이름을 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "아이디", required = true),
            @ApiImplicitParam(name = "name", value = "이름", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "검색 경로를 찾을 수 없음"),
    })
    public ResponseEntity<UserTO> modifyUser(
            @PathVariable("account") String account,
            @RequestBody String name
    ) {
        UserTO user = userService.updateUser(account, name);
        if (user != null) {
            return new ResponseEntity<>(
                    user, null, HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    user, null, HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/users/{account}")
    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "아이디", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "삭제 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "검색 경로를 찾을 수 없음"),
    })
    public ResponseEntity<UserTO> deleteUser(
            @PathVariable("account") String account
    ) {
        String target = userService.deleteUser(account);
        if (target != null) {
            return new ResponseEntity<>(
                    null, null, HttpStatus.NO_CONTENT
            );
        } else {
            return new ResponseEntity<>(
                    null, null, HttpStatus.NOT_FOUND
            );
        }
    }
}

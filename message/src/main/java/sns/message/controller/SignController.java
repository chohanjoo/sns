package sns.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sns.message.config.JwtTokenProvider;
import sns.message.dto.UserDto;
import sns.message.request.AuthenticationRequest;
import sns.message.request.CreateUserRequest;
import sns.message.response.CommonResult;
import sns.message.response.SingleResult;
import sns.message.service.ResponseService;
import sns.message.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class SignController{

    @Autowired
    private UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@RequestBody AuthenticationRequest request) throws Exception {

        UserDto user = userService.retrieveUserById(request.getUsername());

        user.setAuthorities(userService.getAuthorities(user.getId()));
//        Map<String,Object> map = new HashMap<>();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new Exception();

//        map.put("token",responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getAuthorities())));
//        map.put("username",user);
        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getAuthorities()));
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signin(@RequestBody CreateUserRequest request) {

        userService.createAdmin(request);

        return responseService.getSuccessResult();
    }
}
package mwohae.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mwohae.auth.config.JwtTokenProvider;
import mwohae.auth.dto.UserDto;
import mwohae.auth.request.AuthenticationRequest;
import mwohae.auth.request.CreateUserRequest;
import mwohae.auth.response.CommonResult;
import mwohae.auth.response.SingleResult;
import mwohae.auth.service.ResponseService;
import mwohae.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. Auth"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
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
